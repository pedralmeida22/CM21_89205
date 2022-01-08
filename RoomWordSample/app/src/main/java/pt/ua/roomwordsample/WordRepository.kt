package pt.ua.roomwordsample

import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insertWord(word: Word){
        wordDao.insert(word)
    }
}