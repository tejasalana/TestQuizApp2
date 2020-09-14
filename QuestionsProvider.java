package com.example.mytestquiz2;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionsProvider implements Parcelable {

    int mNoOfQuestions = 5;
    String[] mQuestions = {
            "Who is prime minister of india?",
            "Which of these is not a bitwise operator?",
            "Which keyword is used by method to refer to the object that invoked it?",
            "Which of these keywords is used to define interfaces in Java?",
            "Which of these access specifiers can be used for an interface?",
            "Which of the following is correct way of importing an entire package ‘pkg’?",
            "What is the return type of Constructors?",
            "Which of the following package stores all the standard java classes?",
            "Which of these method of class String is used to compare two String objects for their equality?",
            "An expression involving byte, int, & literal numbers is promoted to which of these?"
    };


    String[] mCorrectAnswers = {"modi", "<=", "this", "interface", "public", "import pkg.*", "None of the mentioned", "java", "equals()", "int"};

    String[][] mChoices = {
            {"modi", "trump", "obama", "kim"},
            {"&", "&=", "|=", "<="},
            {"import", "this", "catch", "abstract"},
            {"interface", "inter", "intf", "faceinter"},
            {"public", "protected", "private", "All of the mentioned"},
            {"Import pkg.", "import pkg.*", "Import pkg.*", "import pkg."},
            {"int", "float", "void", "None of the mentioned"},
            {"lang", "java", "util", "java.packages"},
            {"equals()", "Equal()", "isequal()", "Isequals()"},
            {"int", "long", "byte", "float"}
    };

    public QuestionsProvider() {

    }

    public String getQuestion(int a) {
        return mQuestions[a];
    }

    public String getChoice1(int a) {
        return mChoices[a][0];
    }

    public String getChoice2(int a) {
        return mChoices[a][1];
    }

    public String getChoice3(int a) {
        return mChoices[a][2];
    }

    public String getChoice4(int a) {
        return mChoices[a][3];
    }

    public String getCorrectAnswer(int a) {
        return mCorrectAnswers[a];
    }



    protected QuestionsProvider(Parcel in) {
        int rows = in.readInt();
        int columns = in.readInt();
        String[] transitionalArray = in.createStringArray();
        mChoices = restoreChoices(transitionalArray, rows, columns);
        mNoOfQuestions = in.readInt();
        mQuestions = in.createStringArray();
        mCorrectAnswers = in.createStringArray();
    }

    public static final Creator<QuestionsProvider> CREATOR = new Creator<QuestionsProvider>() {
        @Override
        public QuestionsProvider createFromParcel(Parcel in) {
            return new QuestionsProvider(in);
        }

        @Override
        public QuestionsProvider[] newArray(int size) {
            return new QuestionsProvider[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        int rows = mChoices.length;
        int columns = mChoices[rows - 1].length;
        String[] transitionalArray = flattenChoices(mChoices);
        parcel.writeInt(rows);
        parcel.writeInt(columns);
        parcel.writeStringArray(transitionalArray);
        parcel.writeInt(mNoOfQuestions);
        parcel.writeStringArray(mQuestions);
        parcel.writeStringArray(mCorrectAnswers);

    }
    private String[] flattenChoices(String[][] sourceCard) {

        int k = 0;
        String[] targetCard = new String[sourceCard.length * sourceCard[sourceCard.length - 1].length];
        for (String[] aSourceCard : sourceCard) {
            for (String anASourceCard : aSourceCard) {
                targetCard[k] = anASourceCard;
                k++;
            }
        }
        return targetCard;
    }
    private String[][] restoreChoices(String[] sourceCard, int rows, int columns) {

        int k = 0;
        String[][] ChoiceArray = new String[rows][columns];
        for (int i = 0; i < ChoiceArray.length; i++) {
            for (int j = 0; j < ChoiceArray[ChoiceArray.length - 1].length; j++) {
                ChoiceArray[i][j] = sourceCard[k];
                k++;
            }
        }
        return ChoiceArray;
    }
}
