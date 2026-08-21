package in.Santrix.crudSpringBootdemo.Service;


import in.Santrix.crudSpringBootdemo.Entity.Student;
import in.Santrix.crudSpringBootdemo.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
        Student studentResp = studentRepository.save(studentReq);

        return studentResp;
    }

    // select * from student where id = false
    public Student getStudent(Long id) {

        Optional<Student> studentResp = studentRepository.findByIdAndDeletedFalse(id);

        if (studentResp.isPresent()) {
            return studentResp.get();
        }

        return null;
    }

    public List<Student>getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedFalse();
        return studentList;
    }



    public Student updateStudent(Long id, Student studentReq) {

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedFalse(id);

        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setId(studentReq.getId());
        studentToSave.setCourse(studentReq.getCourse());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setDeleted(false);

        return studentRepository.save(studentToSave);
    }


    public Boolean deleteStudent(Long id) {

        Boolean isStudent = studentRepository.existsById(id);

        if (!isStudent) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }


    public Boolean deleteStudentSoftly(Long id) {
        Optional <Student> existingStd = studentRepository.findByIdAndDeletedFalse(id);
        if (existingStd.isEmpty()) {
            return false;
        }
        Student tosave= existingStd.get();
        tosave.setDeleted(true);
        studentRepository.save(tosave);

        return true;
    }


}
