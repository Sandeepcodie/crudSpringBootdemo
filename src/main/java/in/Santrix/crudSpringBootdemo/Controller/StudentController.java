package in.Santrix.crudSpringBootdemo.Controller;

import in.Santrix.crudSpringBootdemo.Entity.Student;
import in.Santrix.crudSpringBootdemo.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students") // for this end point we use this class starts with ./api/students

public class StudentController {

    private final StudentService stdService;

    public StudentController(StudentService stdService) {
        this.stdService = stdService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student std) {
        // calling method of studentservice and return and save agian in variable
        Student createdStudent = stdService.createStudent(std);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {

        Student studentResp = stdService.getStudent(id);

        if (studentResp == null) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(null);
            return ResponseEntity.notFound().build();  // above and this both work
        }
        return ResponseEntity.ok(studentResp);
    }



    @GetMapping("/getALL")
    public ResponseEntity<List<Student>> getAllStudent() {

        List<Student> studentList = stdService.getAllStudent();

        if (studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student studentReq) {

        Student studentResp = stdService.updateStudent(id, studentReq);

        if (studentResp == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        Boolean isDeleted = stdService.deleteStudent(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }

    @PatchMapping("/deleteSoftly")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {
        Boolean isDeleted = stdService.deleteStudentSoftly(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record deleted");
    }

}