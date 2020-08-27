package com.mak.remak.engine.actions;

public class TestAppActions {

	private static Integer method1(String input) {
		System.out.println("Action fired:" + input);
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("Testing Actions...");
		
		FIAction<String, Integer> action1 = new Action<String, Integer>("SomeCommand") {
			@Override
			public Integer execute(String input) {
				System.out.println("Action fired:"+input);
				return 100;
			}
		};
		
		FIAction<String, Integer> action2 = (String input) -> {
			System.out.println("Action fired:" + input);
			return 99;
		};
		
		FIAction<String, Integer> action3 = TestAppActions::method1;
		
		action1.execute("Mehmet");
		action2.execute("Mustafa");
		action3.execute("Aylin");
		
		
	}

}
