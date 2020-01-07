package com.pru_ybs.musicquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pru_ybs.musicquiz.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="MusicQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    QuizHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db =db;

        final String SQL_CREATE_QUESTION_TABLE ="CREATE TABLE " +
                QuestionsTable.TABLE_NAME + "(" +
                QuestionsTable._ID +"INTEGER PRIMARY KEY AUTOINCREMENT," +
                QuestionsTable.COLUMN_QUESTION + "TEXT" +
                QuestionsTable.COLUMN_OPTION1 + "TEXT " +
                QuestionsTable.COLUMN_OPTION2 + "TEXT" +
                QuestionsTable.COLUMN_OPTION3 + "TEXT" +
                QuestionsTable.COLUMN_ANSWER_NR + "INTEGER" +
                ")" ;
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME );
        onCreate(db);


    }
    private void fillQuestionsTable(){
        Question q1 = new Question("Beş paralel çizgi ve dört aralıktan oluşan, müziğin yazıya dökülmesi için kullanılan görseldeki bu aracın adı nedir?", "A:OKTAV" , "B:NOTA" ,"C:PORTRE",1);
        addQuestion(q1);
        Question q2 = new Question("Hangisi müziğin 3 ana öğesinden biri değildir?", "A:ROTA" , "B:RİTM" ,"C:ARMONİ",2);
        addQuestion(q2);
        Question q3 = new Question("Hangisi telli çalgılardan biri değildir?", "A:AKORDİYON" , "B:TAMBUR" ,"C:PİYANO",3);
        addQuestion(q3);
        Question q4 = new Question("Perdesiz gitarın mucidi ünlü sanatçımızın adı nedir?", "A:YAVUZ ÇETİN" , "B:BÜLENT ORTAÇGİL" ,"C:ERKAN OĞUR",4);
        addQuestion(q4);
        Question q5 = new Question("Hangisi müzikteki erkek seslerinden biri değildir? ", "A:BARİTON" , "B:SOPRANA" ,"C:TENOR",5);
        addQuestion(q5);
        Question q6 = new Question("Müzik seslerini yazmak için kullandığımız notalar kaç tanedir?", "A:7" , "B:9" ,"C:8",6);
        addQuestion(q6);
        Question q7 = new Question("Hangisi müzikteki kadın seslerinden biri değildir?", "A:BAS" , "B:MEZZO SOPRANA" ,"C:ALTO",7);
        addQuestion(q7);


    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME,null, cv);
    }

    List<Question> getAllQuestions(){
        List<Question> questionList =new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery( "SELECT  * FROM " + QuestionsTable.TABLE_NAME,null);
        if (c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);





            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
