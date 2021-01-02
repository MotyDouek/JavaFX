package todolist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import todolist.datamodel.TodoItem;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemsDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for Guy",
                LocalDate.of(2021, Month.DECEMBER, 10));
        TodoItem item2 = new TodoItem("Doctors Appointment", "See Dr.Nach",
                LocalDate.of(2021, Month.JANUARY, 23));
        TodoItem item3 = new TodoItem("Finish design for client", "I promised Mike I`d email",
                LocalDate.of(2021, Month.APRIL, 2));
        TodoItem item4 = new TodoItem("Pickup Dug", "From the trainstaion",
                LocalDate.of(2020, Month.MARCH, 15));
        TodoItem item5 = new TodoItem("Pick up dry cleaning", "Should be ready by Sunday",
                LocalDate.of(2021, Month.JANUARY, 3));
        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemsDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemsDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
//        System.out.println("The selected item is " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemsDetailsTextArea.setText(sb.toString());
    }

}
