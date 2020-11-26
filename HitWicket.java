import java.util.*;
class ckt{
	public String batsmanType;
	public String bowlerType;
	public int runs=0;
	public int runsInLastBall=0;
	public String ballName;
	
	public void display(){
		System.out.println("Runs Score "+runs);
		System.out.println("Runs on the Last Ball "+runsInLastBall);
		System.out.println("Current Ball "+ballName);
		System.out.println("Possible Shots:");
		ArrayList<String> shots=getList(ballName);
		for(int i=0;i<shots.size();i++){
			String shot=shots.get(i);
			float shot_modifier=shotModifier(shot);
			float ball_modifier=ballModifier(ballName);
			float hun=100;
			float probability=(((shot_modifier-ball_modifier)*hun)/shot_modifier);
			System.out.println(shot+"-"+NoOfRuns(shot)+"-"+probability);
		}
	}
	
	public int ballModifier(String ball){
		if(ball=="Full toss") return 4;
		if(ball=="Yorker") return 3;
		if(ball=="Bouncer") return 4;
		if(ball=="Slower Ball") return 2;
		else return -1;
	}
	public int shotModifier(String shot){
		if(shot=="Defend") return 5;
		if(shot=="Run") return 7;
		if(shot=="Straight Drive") return 6;
		if(shot=="Square Cut") return 7;
		if(shot=="Helicopter") return 8;
		return -1;
	}
	public int NoOfRuns(String shot){
		System.out.println(shot);
		if(shot=="Defend") return 0;
		if(shot=="Run") return 1;
		if(shot=="Straight Drive") return 2;
		if(shot=="Square Cut") return 4;
		if(shot=="Helicopter") return 6;
		return -1;
	}
	public float getProbability(String ball,String shot){
		float shot_modifier=shotModifier(shot);
		float ball_modifier=ballModifier(ball);
		float hun=100;
		return (((shot_modifier-ball_modifier)*hun)/shot_modifier);
	}
	
	public ArrayList<String> getList(String ballName){
		ArrayList<String> list=new ArrayList<>();
		list.add("Defend");
		list.add("Run");
		if(ballName=="Full toss"){
			list.add("Helicopter");
		}else if(ballName=="Yorker"){
			list.add("Straight Drive");
		}else if(ballName=="Bouncer"){
			list.add("Square Cut");
		}else{
			list.add("Straight Drive");
		}
		return list;
	}

}
public class HitWicket{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ckt newGame=new ckt();
		System.out.println("Enter the Bowler type and Batsman Type");
		newGame.bowlerType=sc.next();
		newGame.batsmanType=sc.next();
		for(int i=0;i<6;i++){
			String ball=getRandomBall();
			newGame.ballName=ball;
			newGame.display();
			String shot=sc.next();
			float prob=newGame.getProbability(ball,shot);
			if(prob>50){
				System.out.println("YOU HIT A "+newGame.NoOfRuns(shot));
				newGame.runs+=newGame.NoOfRuns(shot);
				newGame.runsInLastBall=newGame.NoOfRuns(shot);
			}else{
				System.out.println("You missed");
			}
		}
	}
	public static String getRandomBall(){
		String []balls={"Full toss","Yorker","Bouncer","Slower Ball"};
		int rnd=new Random().nextInt(balls.length);
		return balls[rnd];
	}
}