package com.example.wordle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var counter = 0;

    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }

    // Helper function from Codepath
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    fun resetFields() {
        val editGuessWordIds = listOf(R.id.guessWord0, R.id.guessWord1, R.id.guessWord2)
        val editGuessCheckIds = listOf(R.id.guessCheckWord0, R.id.guessCheckWord1, R.id.guessCheckWord2)

        editGuessWordIds.forEachIndexed { index, i ->
            val thisTextView = findViewById<TextView>(editGuessWordIds[index])
            val thisCheckView = findViewById<TextView>(editGuessCheckIds[index])

            thisTextView.text = ""
            thisCheckView.visibility = View.INVISIBLE

            thisCheckView.text = ""
            thisCheckView.visibility = View.INVISIBLE
        }

        counter = 0

        //val guessButton =
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.submitGuessText)
        val buttonSubmitGuess = findViewById<Button>(R.id.submitGuessButton)
        var resetButton = findViewById<Button>(R.id.submitGuessButtonReset)

        // Get List of element IDs for reusable code
        val editGuessWordIds = listOf(R.id.guessWord0, R.id.guessWord1, R.id.guessWord2)
        val editGuessCheckIds = listOf(R.id.guessCheckWord0, R.id.guessCheckWord1, R.id.guessCheckWord2)
        var userInput = ""
        var answer = getRandomFourLetterWord()
        Log.d("MainScreen", answer)

        resetButton.setOnClickListener {
            resetFields()
            answer = getRandomFourLetterWord()
            buttonSubmitGuess.visibility = View.VISIBLE
            resetButton.visibility = View.INVISIBLE
            Log.d("MainScreen", answer)
        }

        buttonSubmitGuess.setOnClickListener {
            //Toast.makeText(it.context, textView.text, Toast.LENGTH_SHORT).show()
            // Check if input is length 4
            if (textView.text.length != 4) {
                Toast.makeText(it.context, "Guess must be 4 letters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if input only contains letters, if not then toast and return
            textView.text.forEachIndexed { index, char ->
                if (!char.isLetter()) {
                    Toast.makeText(it.context, "Guess must only contain letters", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Change input to lowercase for future prep
            userInput = textView.text.toString().uppercase()

            // Check if answer and increase counter
            // Update and show guessWord
            val thisTextView = findViewById<TextView>(editGuessWordIds[counter])
            thisTextView.visibility = View.VISIBLE
            thisTextView.text = userInput
            // Check if answer is correct, if not show O+
            val thisCheckView = findViewById<TextView>(editGuessCheckIds[counter])
            thisCheckView.visibility = View.VISIBLE
            thisCheckView.text = checkGuess(userInput, answer)
            Log.d("MainScreen", checkGuess(userInput, answer))

            // If the answer was correct then stop the game
            if (thisCheckView.text == "OOOO") {
                Log.d("MainScreen", "GAME OVER")
                buttonSubmitGuess.visibility = View.INVISIBLE
                resetButton.visibility = View.VISIBLE
            } else {
                // If this is the 3rd aka fail Show a reset button
                if (counter == 2) {
                    buttonSubmitGuess.visibility = View.INVISIBLE
                    resetButton.visibility = View.VISIBLE
                }
            }

            // If counter was 3rd aka fail

            // Increase the counter and clear the input field
            counter++
            textView.text = ""
            //Log.d("MainScreen", userInput
        }
    }
}