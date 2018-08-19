package com.ramihussien.countryweathermvvm.ui.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ramihussien.countryweathermvvm.R;
import com.ramihussien.countryweathermvvm.util.SnackBarUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * A base activity for all activities that use {@link ViewDataBinding} along with {@link android.arch.lifecycle.ViewModel}
 * <p>
 * The base activity is responsible for performing dependency injection, data binding and preparing ViewModel
 * that's necessary for sub activities to work properly.
 * <p>
 * Please note that:
 * <ul>
 * <li>Sub activities MUST NOT perform any dependency injection (i.e using {@link AndroidInjection})
 * since it's all handled in the base activity
 * </li>
 * <li>Sub activities MUST NOT perform data binding (i.e using {@link DataBindingUtil#setContentView(Activity, int)})</li>
 * <li>Sub activities should be added to {@link com.ramihussien.countryweathermvvm.di.ActivityModule}
 * and annotated with {@link dagger.android.ContributesAndroidInjector} </li>
 * <li>Sub activities can use {@link #mBinding} and {@link #mViewModel} directly from the base activity</li>
 * <li>Sub activities don't have to call {@link #initViews()} in the {@link #onCreate(Bundle)},
 * since it's called automatically in {@link #onCreate(Bundle)} method in the base activity</li>
 * </ul>
 *
 * @param <DB> the DataBinding class that's associated with the activity
 * @param <VM> the ViewModel class that's associated with the activity
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected DB mBinding;

    protected VM mViewModel;

    @Inject
    ViewModelFactory mViewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();

        super.onCreate(savedInstanceState);

        performDataBinding();

        initViews();
    }

    /**
     * Returns the activity layout resource id.
     *
     * @return layout resource id.
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * Returns the view model that's associated with the activity.
     * <p>
     * When the ViewModel has a non zero argument constructor., it must be provided as follows:
     * <pre>
     *     <code>
     *         ViewModelClass viewModel = ViewModelProviders.of(this, {@link #getViewModelFactory()}).get(ViewModelClass.class);
     *     </code>
     * </pre>
     *
     * @see #getViewModelFactory()
     */
    public abstract VM getViewModel();

    /**
     * Returns the view model binding variable as declared in the layout.
     * <p>
     * For example,
     * <pre>
     *      public int getViewModelBindingVariable() {
     *          return BR.viewModel;
     *     }
     * </pre>
     *
     * @return view model binding variable.
     */
    public abstract int getViewModelBindingVariable();

    /**
     * Sub activities should use this method to initialize UI components
     * such as ({@link android.widget.TextView}, {@link android.support.v7.widget.RecyclerView}, etc.).
     * <p>
     * Called in {@link #onCreate(Bundle)} method in {@link BaseActivity}
     * after performing dependency injection and data binding.
     */
    public abstract void initViews();

    /**
     * Returns the view model factory that will be used to provide view models.
     * <p>
     * Please note that:
     * <ul>
     * <li>Sub activities MUST NOT override this method.</li>
     * <li>Sub activities should only use this method when the view model has a non zero argument constructor.</li>
     * <li>When a view model has a non zero argument constructor.,
     * it should be declared in {@link com.ramihussien.countryweathermvvm.di.ViewModelsModule}</li>
     * </ul>
     * <p>
     * When the view model has a zero argument constructor, it can be provided
     * without the need for {@link ViewModelFactory} as follows:
     * <pre>
     *     <code>
     *         ViewModelClass viewModel = ViewModelProviders.of(this).get(ViewModelClass.class);
     *     </code>
     * </pre>
     *
     * @return {@link ViewModelFactory}
     */
    public final ViewModelFactory getViewModelFactory() {
        return mViewModelFactory;
    }

    protected void showError(String message, View.OnClickListener callback) {
        SnackBarUtils.showWithAction(this, mBinding.getRoot(), message,
                Snackbar.LENGTH_LONG, SnackBarUtils.Action.ERROR,
                getString(R.string.retry), callback);
    }

    @SuppressWarnings("unused")
    protected void showWarning(String message) {
        SnackBarUtils.show(this, mBinding.getRoot(), message,
                Snackbar.LENGTH_LONG, SnackBarUtils.Action.WARNING);
    }

    @SuppressWarnings("unused")
    protected void showSuccess(String message) {
        SnackBarUtils.show(this, mBinding.getRoot(), message,
                Snackbar.LENGTH_LONG, SnackBarUtils.Action.SUCCESS);
    }

    private void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    private void performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mBinding.setVariable(getViewModelBindingVariable(), mViewModel);
        mBinding.executePendingBindings();
    }
}
