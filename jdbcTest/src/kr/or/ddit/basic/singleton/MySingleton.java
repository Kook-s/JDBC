package kr.or.ddit.basic.singleton;

/*
 * singleton 패턴 >> 객체가 1개만 만들어지게 하는 방법
 *                   (외부에서 new 명령을 사용하지 못하게 한다.)
 *  - 사용되는 곳 >>메모리 낭비 방지, 데이터의 공유가 쉽다.
 *  
 *  - singleton 클래스 만드는 방법(필수 구성 요소)
 *    1. 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
 *    2. 모든 생성자의 접급 제한자를 private으로 한다.
 *    3. 자신 class의 인스턴스를 생성하고 반환하는 메서드를 public static으로 작성한다.
 *       (이 메서드의 이름은 보통 getIntance로 한다.)
*/
public class MySingleton {
	// 1번
	private static MySingleton single;

	// 2번
	private MySingleton() {
		System.out.println("생정자 입니다...");
	}

	// 3번
	public static MySingleton getInstance() {
		if (single == null)
			single = new MySingleton();
		return single;

	}

	// 기타 이 클래스가 처리할 내용을 작성하면 된다.
	public void displayTest() {
		System.out.println("이 메시지는 싱글톤 클래스의 메소드에서 보낸 메시지 입니다...");
	}

}
