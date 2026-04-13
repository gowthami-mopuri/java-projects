import java.util.Scanner;
public class seriesnumber{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String [] questions={
                            "2,4,6,8,?",
                            "3,9,12,15,?",
                            "1,4,9,16,?"
                            };
                        
          int[] answers={10, 21, 25};
          int score=0;
          System.out.println("welcome to game");
          for(int i=0;i<questions.length;i++)
          {
           System.out.println("Question"+(i+1)+":\n"+questions[i]+"\nyour answer: ");
           int answer=sc.nextInt();
           if(answer==answers[i])
           {
            System.out.println("correct!");
            score++;
           }
           else{
            System.out.println("wrong! the correct answer is"+answers[i]);

           }
          }
          System.out.println("your final score is"+score);

    }
}