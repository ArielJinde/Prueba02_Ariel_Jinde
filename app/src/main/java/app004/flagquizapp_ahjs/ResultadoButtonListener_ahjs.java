package app004.flagquizapp_ahjs;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import app004.flagquizapp.R;

public class ResultadoButtonListener_ahjs implements OnClickListener {
    private MainActivityFragment_ahjs mainActivityFragmentAhjs;
    private Handler handler;

    public ResultadoButtonListener_ahjs(MainActivityFragment_ahjs mainActivityFragmentAhjs) {
        this.mainActivityFragmentAhjs = mainActivityFragmentAhjs;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.mainActivityFragmentAhjs.getQuizViewModel().getCorrectCountryName();
        this.mainActivityFragmentAhjs.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.mainActivityFragmentAhjs.getQuizViewModel().setCorrectAnswers(1);
            this.mainActivityFragmentAhjs.getAnswerTextView().setText(answer + "!");
            this.mainActivityFragmentAhjs.getAnswerTextView().setTextColor(
                    this.mainActivityFragmentAhjs.getResources().getColor(R.color.correct_answer));

            this.mainActivityFragmentAhjs.disableButtons();

            if (this.mainActivityFragmentAhjs.getQuizViewModel().getCorrectAnswers()
                    == LogicaDePreguntas__ahjs.getFlagsInQuiz()) {
                ResultadosDialogFragment_ahjs quizResults = new ResultadosDialogFragment_ahjs();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.mainActivityFragmentAhjs.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(LogicaDePreguntas__ahjs.getTag(),
                            "GuessButtonListener: this.mainActivityFragment.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                mainActivityFragmentAhjs.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.mainActivityFragmentAhjs.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}
