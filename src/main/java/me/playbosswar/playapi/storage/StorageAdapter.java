package me.playbosswar.playapi.storage;

import me.playbosswar.playapi.storage.exceptions.AdapterSetupFailedException;
import me.playbosswar.playapi.storage.exceptions.AdapterTransactionException;

import java.io.IOException;
import java.util.List;

public interface StorageAdapter<T> {
    /**
     * @return - Adapter name
     */
    String getName();

    /**
     * Perform the required actions to setup the adapter
     *
     * Will be called when adapter is required for loaded implementation
     *
     * Take into account that this can happen multiple times depending on how many classes are registered
     */
    void setup() throws AdapterSetupFailedException, IOException;

    void insert(T data) throws AdapterTransactionException;
    T getOne(Object query) throws AdapterTransactionException;
    List<T> getAll(Object query) throws AdapterTransactionException;
    void deleteOne(Object query) throws AdapterTransactionException;
    void deleteAll(Object query) throws AdapterTransactionException;
    void updateOne(Object query) throws AdapterTransactionException;
    void updateMany(Object query) throws AdapterTransactionException;
}
