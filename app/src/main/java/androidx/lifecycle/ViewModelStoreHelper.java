package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;

import java.util.Set;

public class ViewModelStoreHelper {

    private final ViewModelStore store;

    public ViewModelStoreHelper(@NonNull ViewModelStore store) {
        this.store = store;
    }

    public void put(String key, ViewModel vm) {
        store.put(key, vm);
    }

    public ViewModel get(String key) {
        return store.get(key);
    }

    public Set<String> keys() {
        return store.keys();
    }
}
