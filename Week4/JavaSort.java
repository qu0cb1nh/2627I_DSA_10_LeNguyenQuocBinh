import java.io.*;
import java.util.*;

public class Solution {
    public static class Student implements Comparable {
        private int id;
        private String name;
        private float cgpa;

        public Student(int id, String name, float cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }
        
        public String getName() {
            return this.name;
        }

        @Override
        public int compareTo(Object o) {
            Student other = (Student) o;
            if(other.cgpa != this.cgpa)
                return Float.compare(other.cgpa, this.cgpa);
            if(this.name != other.name) {
                return this.name.compareTo(other.name);
            }
            return Integer.compare(this.id, other.id);
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int id = sc.nextInt();
            String name = sc.next();
            float cgpa = sc.nextFloat();
            students.add(new Student(id, name, cgpa));
        }
        Collections.sort(students);
        for(Student s : students) {
            System.out.println(s.getName());
        }
    }
}