// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter03/AutoboxingUnboxing.java

public class AutoboxingUnboxing
{
	public static void main( String[] args ) {
		int primitiveInt = 100;
		Integer referenceInt = 200;
		String stringInt = "300";
		System.out.println( "primitive=" + primitiveInt + " reference=" + referenceInt + " string=" +  stringInt );
		// 첫째, wrapper class 활용 예 살펴보기
		referenceInt = Integer.parseInt( stringInt );
		System.out.println( "primitive=" + primitiveInt + " reference=" + referenceInt + " string=" +  stringInt );
		referenceInt = 400;
		stringInt = referenceInt.toString();
		System.out.println( "primitive=" + primitiveInt + " reference=" + referenceInt + " string=" +  stringInt );
		// 둘째, autoboxing 활용 예 살펴보기
		referenceInt = primitiveInt;
		System.out.println( "primitive=" + primitiveInt + " reference=" + referenceInt + " string=" +  stringInt );
		referenceInt = 500;
		// 셋째, unboxing 활용 예 살펴보기
		primitiveInt = referenceInt;
		System.out.println( "primitive=" + primitiveInt + " reference=" + referenceInt + " string=" +  stringInt );
	}
}

