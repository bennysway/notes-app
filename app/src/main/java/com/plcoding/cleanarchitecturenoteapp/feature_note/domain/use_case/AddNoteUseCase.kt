package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        // ps we dont want to add an empty note
        // but checking for an empty not is business logic
        // and belongs here, not in the vm
        // if something is not successful, try returning exception classes etc

        if (note.title.isBlank()) throw InvalidNoteException("The title cannot be empty.")
        if (note.content.isBlank()) throw InvalidNoteException("The content cannot be empty.")
        repository.insertNote(note)

    }
}