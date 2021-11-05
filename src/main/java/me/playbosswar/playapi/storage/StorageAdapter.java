package me.playbosswar.playapi.storage;

import me.playbosswar.playapi.storage.exceptions.AdapterSetupFailedException;
import me.playbosswar.playapi.storage.exceptions.AdapterTransactionException;

import java.io.IOException;
import java.util.List;

/**
 * Specific adapter to store data
 * @param <T> - Class type of the class to be stored
 * @param <Q> - Query structure used to retrieve data
 */
public interface StorageAdapter<T, Q> {
    /**
     * Perform the required actions to setup the adapter
     *
     * Will be called when adapter is required for loaded implementation
     *
     * Take into account that this can happen multiple times depending on how many classes are registered
     */
    void setup() throws AdapterSetupFailedException, IOException;

    void insert(T data) throws AdapterTransactionException;
    T findOne(Q query) throws AdapterTransactionException;
    List<T> find(Q query) throws AdapterTransactionException;
    List<T> findAll() throws AdapterTransactionException;
    void deleteOne(Q query) throws AdapterTransactionException;
    void deleteAll(Q query) throws AdapterTransactionException;
    void updateOne(Q query) throws AdapterTransactionException;
    void updateMany(Q query) throws AdapterTransactionException;
}
