package Services;

public class DepartmentGenerate {
    private static final int MIN_NUMBER_DEPARTMENT = 1;
    private static final int MAX_NUMBER_DEPARTMENT = 5;
    public int generateDepartmentInRange(int min, int max){
        return (int) (Math.random() * (max - min)) + min;
    }
    public int generateDepartment(){
        return generateDepartmentInRange(MIN_NUMBER_DEPARTMENT,MAX_NUMBER_DEPARTMENT);
    }
}
