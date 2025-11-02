// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter02/WeatherNews.java

import java.util.Scanner;
public class WeatherNews
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 주요 날씨 내용을 입력
		System.out.print( "오늘의 기상특보는 무엇인가요: " );
		String weatherReport = scan.next();
		System.out.print( "오늘의 주의사항은 무엇인가요: " );
		String precaution = scan.next();
		System.out.print( "오늘은 무슨 요일인가요: " );
		String week = scan.next();
		System.out.print( "오늘은 몇 월인가요: " );
		int month = scan.nextInt();
		System.out.print( "오늘은 며칠인가요: " );
		int day = scan.nextInt();
		System.out.print( "서울 최저기온은 몇 도인가요: " );
		int lowSeoul = scan.nextInt();
		System.out.print( "춘천 최저기온은 몇 도인가요: " );
		int lowChuncheon = scan.nextInt();
		System.out.print( "대전 최저기온은 몇 도인가요: " );
		int lowDaejeon = scan.nextInt();
		System.out.print( "광주 최저기온은 몇 도인가요: " );
		int lowGwangju = scan.nextInt();
		System.out.print( "부산 최저기온은 몇 도인가요: " );
		int lowBusan = scan.nextInt();
		System.out.print( "제주 최저기온은 몇 도인가요: " );
		int lowJeju = scan.nextInt();
		System.out.print( "서울 최고기온은 몇 도인가요: " );
		int highSeoul = scan.nextInt();
		System.out.print( "춘천 최고기온은 몇 도인가요: " );
		int highChuncheon = scan.nextInt();
		System.out.print( "대전 최고기온은 몇 도인가요: " );
		int highDaejeon = scan.nextInt();
		System.out.print( "광주 최고기온은 몇 도인가요: " );
		int highGwangju = scan.nextInt();
		System.out.print( "부산 최고기온은 몇 도인가요: " );
		int highBusan = scan.nextInt();
		System.out.print( "제주 최고기온은 몇 도인가요: " );
		int highJeju = scan.nextInt();
		// 날씨 정보를 바탕으로 기상 뉴스를 자동으로 생성
		System.out.println( month + "월 " + day + "일 " + week + "요일 오늘의 날씨입니다. " );
		System.out.println( "주요 지역의 아침 최저 기온은 서울 " + lowSeoul + "도, 춘천 " + lowChuncheon + "도, 대전 " + lowDaejeon + "도, 광주 " + lowGwangju + "도, 부산 " + lowBusan + "도, 제주 " + lowJeju + "도입니다. " );
		System.out.println( "낮 최고기온은 서울 " + highSeoul + "도, 춘천 " + highChuncheon + "도, 대전 " + highDaejeon + "도, 광주 " + highGwangju + "도, 부산 " + highBusan + "도, 제주 " + highJeju + "도입니다. " );
		System.out.println( weatherReport + "가 발효중인 지역에서는 " + precaution + " 운전에 각별히 주의해 주시기 바랍니다. " );
		scan.close();
	}
}

