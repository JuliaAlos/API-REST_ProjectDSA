package edu.upc.dsa;

import edu.upc.dsa.models.ForumEntry;

import java.util.List;

public interface ForumManager {

    void addEntry (ForumEntry forumEntry);
    List<ForumEntry> getAllEntries ();
}
