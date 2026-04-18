@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    
    // Initialize DB here when the View is confirmed to exist
    dbHelper = new ProjectDatabaseHelper(requireContext());
    
    // Load data only after initialization
    notesInput.setText(dbHelper.getNotes());
}
