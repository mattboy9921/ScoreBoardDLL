public class Score {

    private int score;

    public Score(int score) {
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int compareTo(Score s) {
        if (score > s.getScore())
            return 1;
        else if (score == s.getScore())
            return 0;
        else
            return -1;
    }
}
