package ru.stqa.jft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Entries extends ForwardingSet<EntryData> {

    private Set<EntryData> delegate;

    public Entries(Entries entries) {
        this.delegate = new HashSet<>(entries.delegate);
    }

    public Entries() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<EntryData> delegate() {
        return delegate;
    }

    public Entries withAdded(EntryData entry) {
        Entries entries = new Entries(this);
        entries.add(entry);
        return entries;
    }

    public Entries without(EntryData entry) {
        Entries entries = new Entries(this);
        entries.remove(entry);
        return entries;
    }
}
