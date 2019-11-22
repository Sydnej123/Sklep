package pl.InternetowySklepMuzyczny.sklep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.InternetowySklepMuzyczny.sklep.fxml_controllers.LoginScreenController;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "pl.InternetowySklepMuzyczny.sklep")
@EntityScan(basePackages = "pl.InternetowySklepMuzyczny.sklep")
public class SklepApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception{
		springContext = SpringApplication.run(SklepApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login_register_window.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		LoginScreenController.springContext = springContext;
		rootNode = fxmlLoader.load();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(rootNode));
		primaryStage.show();
	}
	@Override
	public void stop() throws Exception{
		springContext.close();
	}
}
