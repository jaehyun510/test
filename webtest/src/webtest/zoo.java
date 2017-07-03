package webtest;

class Animal {
	int leg = -1; 
	int legs(){
		return leg;
	}
}
class Dog extends Animal{
	int leg = 4; 
	int legs(){
		return leg;
	}
}
class Fish extends Animal{
	int leg = 0; 
	int legs(){
		return leg;
	}
}
class Bird extends Animal{
	int leg = 2; 
	int legs(){
		return leg;
	}
}
public class zoo{
	public static void main(String[] args){
		Animal pet[]= {new Dog(), new Bird(), new Fish()};
		for (int i = 0; i < 3; i++) {
			System.out.println(pet[i].leg+ " " + pet[i].legs());
		}
	}
}