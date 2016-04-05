package controller.cenum;

public enum TaskTypeEnum {

	Need("0","需求"), Bug("1","bug");
	private final String name;
	
	private final String code;

	private TaskTypeEnum(String code,String name) {
		this.code=code;
		this.name = name;
	}

	public static String resStatus(int i) {
		String result="";
		switch (i) {
		case 0:
			result = TaskTypeEnum.Need.getName();
			break;
		case 1:
			result = TaskTypeEnum.Bug.getName();
			break;
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
