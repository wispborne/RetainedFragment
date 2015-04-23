# RetainedFragment
Implementation of a retaining fragment that does most of the hard work.

###Example retrieval usage
```java
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     // other createView stuff
     
     RetainedFragment fragment;
     if ((fragment = RetainedFragment.find(getFragmentManager(), TAG_RETAINED_FRAGMENT)) != null) {
          retrievedObject = fragment.get(TAG_RETAINED_FRAGMENT_ITEM);
     }
     
     // more createView stuff
}
```
 
###Example put usage
 ```java
public void onSaveInstanceState(Bundle outState) {
     // save instance state stuff
     
     RetainedFragment fragment = RetainedFragment.create(getFragmentManager(), TAG_RETAINED_FRAGMENT);
     fragment.put(TAG_RETAINED_FRAGMENT_ITEM, storedObject);
     
     // more save instance state stuff
}
```
