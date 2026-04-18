package com.darvin.codeedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProjectNotesFragment extends Fragment {

    private EditText notesInput;
    private ProjectDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        notesInput = view.findViewById(R.id.notesInput);
        dbHelper = new ProjectDatabaseHelper(getContext());
        
        // Load existing notes if they exist
        notesInput.setText(dbHelper.getNotes()); 
        
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        // Auto-save logic: Save notes to SQLite when leaving the tab
        String currentNotes = notesInput.getText().toString();
        dbHelper.saveNotes(currentNotes);
    }
}
