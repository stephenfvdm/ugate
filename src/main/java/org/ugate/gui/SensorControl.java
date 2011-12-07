package org.ugate.gui;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import org.ugate.UGateUtil;
import org.ugate.gui.components.Gauge.IndicatorType;
import org.ugate.gui.components.UGateToggleSwitchPreferenceView;
import org.ugate.gui.components.UGateGaugePreferenceView;
import org.ugate.resources.RS;

/**
 * Sensor/Gate control view
 */
public class SensorControl extends ControlPane {

	public static final double LABEL_WIDTH = 100d;
	public static final String FORMAT_SONAR = "%04.1f";
	public static final String FORMAT_PIR = "%04.1f";
	public static final Color COLOR_SONAR = Color.TURQUOISE;
	public static final Color COLOR_PIR = Color.RED;
	public static final String FORMAT_MW = "%03d";
	public static final Color COLOR_MW = Color.CHARTREUSE;
	
	public SensorControl(final ScrollPane helpText) {
		super(helpText);
		addSensorMovementAdjustments();
		addSensorChildren();
	}
	
	protected void addSensorMovementAdjustments() {
		final GridPane grid = new GridPane();
		final Label sonarPirPanHeader = new Label("Sonar/PIR Pan Angle");
		sonarPirPanHeader.setWrapText(true);
		sonarPirPanHeader.setPrefWidth(LABEL_WIDTH);
		sonarPirPanHeader.getStyleClass().add("gauge-header");
		grid.add(sonarPirPanHeader, 0, 0);
		final Label sonarPirTiltHeader = new Label("Sonar/PIR Tilt Angle");
		sonarPirTiltHeader.setWrapText(true);
		sonarPirTiltHeader.setPrefWidth(LABEL_WIDTH);
		sonarPirTiltHeader.getStyleClass().add("gauge-header");
		grid.add(sonarPirTiltHeader, 1, 0);
		final Label headerMW = new Label("Microwave Pan Angle");
		headerMW.setWrapText(true);
		headerMW.setPrefWidth(LABEL_WIDTH);
		headerMW.getStyleClass().add("gauge-header");
		grid.add(headerMW, 0, 2);
		final UGateGaugePreferenceView sonarPirPanGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_SONAR_IR_ANGLE_PAN_KEY, null, IndicatorType.KNOB, KNOB_SIZE_SCALE,
				10d, 0, 0, 180d, 19, 0, FORMAT_ANGLE, RS.IMG_PAN, COLOR_PAN_TILT);
		addHelpText(sonarPirPanGauge, "Sonar/PIR Pan: Current trip alram sensor pan angle (in degrees)");
		grid.add(sonarPirPanGauge, 0, 1);
		final ImageView sonarPirTiltImgView = RS.imgView(sonarPirPanGauge.imageView.getImage());
		sonarPirTiltImgView.setRotate(90d);
		final UGateGaugePreferenceView sonarPirTiltGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_SONAR_IR_ANGLE_TILT_KEY, null, IndicatorType.KNOB, KNOB_SIZE_SCALE,
				10d, 0, 0, 180d, 19, 0, FORMAT_ANGLE, sonarPirTiltImgView, COLOR_PAN_TILT);
		addHelpText(sonarPirTiltGauge, "Sonar/PIR Tilt: Current trip alarm sensor tilt angle (in degrees)");
		grid.add(sonarPirTiltGauge, 1, 1);
		final UGateGaugePreferenceView mwPanGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_MW_ANGLE_PAN_KEY, null, IndicatorType.KNOB, KNOB_SIZE_SCALE,
				10d, 0, 0, 180d, 19, 0, FORMAT_ANGLE, RS.IMG_PAN, COLOR_PAN_TILT);
		addHelpText(mwPanGauge, "Microwave Pan: Current trip alarm sensor pan angle (in degrees)");
		grid.add(mwPanGauge, 0, 3);
		
		final Group cell = createCell(false, true, grid);
		add(cell, 0, 0);
	}
	
	protected void addSensorChildren() {
		final GridPane grid = new GridPane();
		final Label headerSonar = new Label("Sonar Configuration");
		headerSonar.getStyleClass().add("gauge-header");
		grid.add(headerSonar, 0, 0);
		final Label headerPIR = new Label("PIR Configuration");
		headerPIR.getStyleClass().add("gauge-header");
		grid.add(headerPIR, 1, 0);
		final Label headerMW = new Label("Micorwave Configuration");
		headerMW.getStyleClass().add("gauge-header");
		grid.add(headerMW, 2, 0);
		final UGateToggleSwitchPreferenceView sonarToggleSwitchView = new UGateToggleSwitchPreferenceView(UGateUtil.SV_SONAR_ALARM_ON_KEY, 
				RS.IMG_SONAR_ALARM_ON, RS.IMG_SONAR_ALARM_OFF);
		addHelpText(sonarToggleSwitchView, 
				"Toggle sonar intruder alarm that when tripped takes a picture, stores it on the computer, and sends email notification with image attachment (if on)");
		grid.add(sonarToggleSwitchView, 0, 1);
		final UGateGaugePreferenceView sonarTripGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_SONAR_DISTANCE_THRES_FEET_KEY, UGateUtil.SV_SONAR_DISTANCE_THRES_INCHES_KEY, 
				IndicatorType.NEEDLE, NEEDLE_SIZE_SCALE,
				1d, 2, 0, 180d, 9, 4, FORMAT_SONAR, RS.IMG_RULER, COLOR_SONAR);
		addHelpText(sonarTripGauge, 
				"Sonar Distance Threshold: Distance at which an image will be taken and sent to the computer and recipients (if alarm is turned on)");
		sonarTripGauge.gauge.setIntensity(80d, 15d, 5d);
		grid.add(sonarTripGauge, 0, 2);
		final UGateGaugePreferenceView sonarTripRateGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_SONAR_DELAY_BTWN_TRIPS_KEY, null, IndicatorType.NEEDLE, NEEDLE_SIZE_SCALE,
				1d, 0, 0, 180d, 61, 0, FORMAT_DELAY, RS.IMG_STOPWATCH, COLOR_SONAR);
		addHelpText(sonarTripRateGauge, 
				"Sonar Delay Between Photos: Delay in minutes between pictures taken/sent when an object is within the distance threshold.\n" +
				"When zero, there may still be a few seconds beween photos due to the wireless transfer rate");
		sonarTripRateGauge.gauge.setIntensity(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
		grid.add(sonarTripRateGauge, 0, 3);
		final UGateToggleSwitchPreferenceView pirToggleSwitchView = new UGateToggleSwitchPreferenceView(UGateUtil.SV_IR_ALARM_ON_KEY, 
				RS.IMG_IR_ALARM_ON, RS.IMG_IR_ALARM_OFF);
		addHelpText(pirToggleSwitchView, 
				"Toggle PIR intruder alarm that when tripped takes a picture, stores it on the computer, and sends email notification with image attachment (if on)");
		grid.add(pirToggleSwitchView, 1, 1);
//		final UGateGaugeDisplay pirTripGauge = new UGateGaugeDisplay(IndicatorType.NEEDLE, NEEDLE_SIZE_SCALE,
//				1d, 2, 0, 180d, 24, 1, 15d, FORMAT_PIR, RS.IMG_RULER,
//				"PIR Distance Threshold: Distance at which an image will be taken and sent to the computer and recipients (if alarm is turned on)", 
//				COLOR_PIR);
//		grid.add(pirTripGauge, 1, 2);
		final UGateGaugePreferenceView pirTripRateGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_IR_DELAY_BTWN_TRIPS_KEY, null, IndicatorType.NEEDLE, NEEDLE_SIZE_SCALE,
				1d, 0, 0, 180d, 61, 0, FORMAT_DELAY, RS.IMG_STOPWATCH, COLOR_PIR);
		addHelpText(pirTripRateGauge, 
				"PIR Delay Between Photos: Delay in minutes between pictures taken/sent when an object is within the distance threshold.\n" +
				"When zero, there may still be a few seconds beween photos due to the wireless transfer rate");
		pirTripRateGauge.gauge.setIntensity(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
		grid.add(pirTripRateGauge, 1, 3);

		final UGateToggleSwitchPreferenceView mwToggleSwitchView = new UGateToggleSwitchPreferenceView(UGateUtil.SV_MW_ALARM_ON_KEY, 
				RS.IMG_MICROWAVE_ALARM_ON, RS.IMG_MICROWAVE_ALARM_OFF);
		addHelpText(mwToggleSwitchView, 
				"Toggle Microwave intruder alarm that when tripped takes a picture, stores it on the computer, and sends email notification with image attachment (if on)");
		grid.add(mwToggleSwitchView, 2, 1);
		final UGateGaugePreferenceView mwTripGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_MW_SPEED_THRES_CYCLES_PER_SEC_KEY, null, IndicatorType.NEEDLE, NEEDLE_SIZE_SCALE,
				1d, 2, 0, 180d, 50, 0, FORMAT_MW, RS.IMG_SPEEDOMETER, COLOR_MW);
		addHelpText(mwTripGauge, 
				"Microwave Speed Threshold: Cycles/Second at which an image will be taken and sent to the computer and recipients (if alarm is turned on)");
		grid.add(mwTripGauge, 2, 2);
		final UGateGaugePreferenceView mwTripRateGauge = new UGateGaugePreferenceView(
				UGateUtil.SV_MW_DELAY_BTWN_TRIPS_KEY, null, IndicatorType.NEEDLE, NEEDLE_SIZE_SCALE,
				1d, 0, 0, 180d, 61, 0, FORMAT_DELAY, RS.IMG_STOPWATCH, 
				COLOR_MW);
		addHelpText(mwTripRateGauge, 
				"Microwave Delay Between Photos: Delay in minutes between pictures taken/sent when an object is within the speed threshold.\n" +
				"When zero, there may still be a few seconds beween photos due to the wireless transfer rate");
		mwTripRateGauge.gauge.setIntensity(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
		grid.add(mwTripRateGauge, 2, 3);
		
		final Group camCell = createCell(false, true, grid);
		add(camCell, 1, 0);
	}
}