package repositories;

import models.Student;
import models.User;
import singletones.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentsDaoImpl implements StudentsDao {
    private Connection connection;

    public StudentsDaoImpl() {
        this.connection = ConnectionProvider.getConnection();
    }

    //language=sql
    private final String SQL_SELECT_BY_NAME_LIKE = "SELECT * FROM students where name like ?";

    @Override
    public List<Student> findAllStudentsWithNameLikePattern(String pattern) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_LIKE)) {
            preparedStatement.setString(1, "%" + pattern + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    students.add(studentRowMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            //Если операция провалилась, обернём пойманное исключение в непроверяемое и пробросим дальше(best-practise)
            throw new IllegalStateException(e);
        }
        return students;
    }

    //language=sql
    private final String SQL_SAVE = "INSERT into students(age, course, name, surname, deducted) values (?,?,?,?,?)";

    @Override
    public void save(Student model) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, model.getAge());
            preparedStatement.setInt(2, model.getCourse());
            preparedStatement.setString(3, model.getName());
            preparedStatement.setString(4, model.getSurname());
            preparedStatement.setBoolean(5, model.isDeducted());
            int updRows = preparedStatement.executeUpdate();
            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключений
                throw new SQLException();
            }
            //Достаём созданное Id пользователя
            try (ResultSet set = preparedStatement.getGeneratedKeys();) {
                //Если id  существет,обновляем его у подели.
                if (set.next()) {
                    model.setId(set.getLong(1));
                } else {
                    //Модель сохранилась но не удаётся получить сгенерированный id
                    //Возбуждаем соответвующее исключение
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=sql
    public static final String SQLUpdate = "UPDATE students SET name = ?, age = ?, course = ?, surname = ?, deducted = ?, filepath = ?  WHERE id = ?";

    @Override
    public void update(Student model) {
        try (PreparedStatement statement = connection.prepareStatement(SQLUpdate)) {
            //На место соответвующих вопросительных знаков уставнавливаем параметры модели, которую мы хотим обновить
            statement.setString(1, model.getName());
            statement.setInt(2, model.getAge());
            statement.setInt(3, model.getCourse());
            statement.setString(4, model.getSurname());
            statement.setBoolean(5, model.isDeducted());
            statement.setString(6, model.getImagePath());
            statement.setLong(7, model.getId());
            //Выполняем запрос и сохраняем колличество изменённых строк
            int updRows = statement.executeUpdate();

            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключений
                throw new SQLException();
            }
        } catch (SQLException e) {
            //Если обноление провалилось, обернём пойманное исключение в непроверяемое и пробросим дальше(best-practise)
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Long aLong) {

    }

    private final String FIND_ONE_BY_ID = "SELECT * FROM students where id = ?";

    @Override
    public Optional<Student> find(Long id) {
        Student student = null;
        //id не может быть меньше 0(в противном случае ничего не возвращаем).
        if (id < 0L) return Optional.empty();
        //Создаём новый объект Statement
        //Использование try-with-resources необходимо для арантированного закрытия statement,
        // вне зависимости от успешности операции.
        //Достаём найденную строку из таблица.
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    student = studentRowMapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            //Если операция провалилась, обернём пойманное исключение в непроверяемое и пробросим дальше(best-practise)
            throw new IllegalStateException(e);
        }
        //Возвращаем объект пользователя из бд.
        //чем отличается ofNullable от of
        return Optional.of(student);
    }

    //language=sql
    private final String FIND_ALL = "SELECT * FROM students";

    @Override
    public List<Student> findAll() {
        Student student;
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                student = studentRowMapper.mapRow(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return students;
    }

    private RowMapper<Student> studentRowMapper = row -> {
        //Получаем id из соответствующей колонки
        Long id = row.getLong("id");
        //Получаем имя из соответсвующей колонки
        String firstName = row.getString("name");
        //Получаем фамилию из соответсвующей колонки
        String lastName = row.getString("surname");
        Boolean deducted = row.getBoolean("deducted");
        //Получаем возраст из соответветсвующей колонки
        Integer age = row.getObject("age", Integer.class);
        Integer course = row.getObject("course", Integer.class);
        String filePath = row.getString("filepath");
        //создаём и возвращаем объект User из полученных данных
        return new Student(id, age, course, firstName, lastName, deducted,filePath);
    };

}
