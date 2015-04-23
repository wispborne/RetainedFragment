package ro.ceva;
 
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
 
import java.util.HashMap;
import java.util.Map;
 
/**
 * A simple non-UI Fragment that stores Objects and is retained over configuration
 * changes.
 * <p/>
 * Example retrieval usage:
 * <pre>
 * public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 *      // other createView stuff
 *
 *      RetainedFragment fragment;
 *
 *      if ((fragment = RetainedFragment.find(getFragmentManager(), TAG_RETAINED_FRAGMENT)) != null) {
 *           retrievedObject = fragment.get(TAG_RETAINED_FRAGMENT_ITEM);
 *      }
 *
 *      // more createView stuff
 * }
 * </pre>
 * <p/>
 * Example put usage:
 * <pre>
 * public void onSaveInstanceState(Bundle outState) {
 *      // save instance state stuff
 *
 *      RetainedFragment fragment = RetainedFragment.create(getFragmentManager(), TAG_RETAINED_FRAGMENT);
 *      fragment.put(TAG_RETAINED_FRAGMENT_ITEM, storedObject);
 *
 *      // more save instance state stuff
 * }
 * </pre>
 */
public class RetainedFragment extends Fragment {
    private Map<String, Object> objectMap = new HashMap<>();
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Sets this Fragment to be retained over a configuration change
        // such as a orientation, language, or date format change
        setRetainInstance(true);
    }
 
    /**
     * Checks to see if the fragment with the specified tag exists.
     *
     * @param fragmentManager The support fragment manager
     * @param tag             The tag to use for the saved fragment instance
     * @return True if the fragment exists or false otherwise.
     */
    public static boolean exists(@NonNull final android.support.v4.app.FragmentManager fragmentManager, @NonNull final String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        return fragment != null && fragment instanceof RetainedFragment;
    }
 
    /**
     * Finds an existing fragment for the specified tag.
     *
     * @param fragmentManager The support fragment library
     * @param tag             The tag to use for the saved fragment instance
     * @return The existing fragment if found, else null.
     */
    @Nullable
    public static RetainedFragment find(@NonNull final android.support.v4.app.FragmentManager fragmentManager, @NonNull final String tag) {
        return exists(fragmentManager, tag)
                ? (RetainedFragment) fragmentManager.findFragmentByTag(tag)
                : null;
    }
 
    /**
     * Searches for an instance with the specified tag and returns it if found or creates one if not.
     *
     * @param fragmentManager The support fragment manager
     * @param tag             The tag to use for the saved fragment instance
     * @return New or existing fragment for the specified tag
     */
    @NonNull
    public static RetainedFragment create(@NonNull final android.support.v4.app.FragmentManager fragmentManager, @NonNull final String tag) {
        RetainedFragment retainingFragment;
 
        if (exists(fragmentManager, tag)) {
            //noinspection ConstantConditions
            return find(fragmentManager, tag);
        } else {
            fragmentManager.beginTransaction().add(retainingFragment = new RetainedFragment(), tag).commitAllowingStateLoss();
        }
 
        return retainingFragment;
    }
 
    /**
     * Store a single object in this Fragment.
     *
     * @param key    The key for the retained object
     * @param object The object to store. May not be null.
     */
    public void put(@NonNull final String key, @NonNull final Object object) {
        objectMap.put(key, object);
    }
 
    /**
     * Get a stored object. Type checking is disabled - miscasting the result will cause an error at runtime.
     *
     * @param key The key for the retained object
     * @return The stored object
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public <T> T get(@NonNull final String key) {
        return (T) objectMap.get(key);
    }
}