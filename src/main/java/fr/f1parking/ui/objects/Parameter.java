package fr.f1parking.ui.objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public abstract class Parameter {

	protected final GridPane content;
	protected final Text parameterName;
	
	protected boolean unsavedChange = false;
	
	public Parameter(String paramName) {
		content = new GridPane();
		
		final RowConstraints heightCons = new RowConstraints();
		heightCons.setMinHeight(75);
		
		final ColumnConstraints leftColCons = new ColumnConstraints();
		leftColCons.setPercentWidth(50);
		final ColumnConstraints rightColCons = new ColumnConstraints();
		rightColCons.setPercentWidth(50);

		content.getRowConstraints().add(heightCons);
		content.getColumnConstraints().addAll(leftColCons, rightColCons);
		content.setStyle("-fx-background-color: #18181f");
		
		//Set parameter name
		parameterName = new Text(paramName);
		parameterName.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");
		//Set parameter flow pane
		final FlowPane parameterNamePane = new FlowPane(parameterName);
		parameterNamePane.setAlignment(Pos.CENTER_LEFT);
		parameterNamePane.setPadding(new Insets(0, 0, 0, 15));
		
		content.add(parameterNamePane, 0, 0);
	}
	
	public abstract void refresh();
	
	public abstract void save();
	
	public GridPane getContent() {
		return content;
	}
	
}
