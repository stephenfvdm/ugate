package org.ugate.wireless.data;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.ugate.RemoteSettings;
import org.ugate.UGateKeeper;


/**
 * Settings data
 */
public class SettingsData extends RxData {

	private int universalRemoteOn;
	private KeyCodes keyCodes;
	private int camResolution;
	private int gateAlarmOn;
	private int sonarDistanceThresholdFeet;
	private int sonarDistanceThresholdInches;
	private int sonarDelayBetweenTrips;
	private int irDistanceThresholdFeet;
	private int irDistanceThresholdInches;
	private int irDelayBetweenTrips;
	private int mwSpeedThresholdCyclesPerSecond;
	private int mwDelayBetweenTrips;
	private int multiAlarmTripState;
	private int camSonarPriority;
	private int camSonarTripPanAngle;
	private int camSonarTripTiltAngle;
	private int camPirPriority;
	private int camPirTripPanAngle;
	private int camPirTripTiltAngle;
	private int camMicrowavePriority;
	private int camMicrowaveTripPanAngle;
	private int camMicrowaveTripTiltAngle;
	private int camLaserPriority;
	private int camLaserTripPanAngle;
	private int camLaserTripTiltAngle;
	
	/**
	 * Constructs settings data with the current preference data
	 * 
	 * @param nodeIndex the remote node index
	 * @param nodeIndex the index of the remote node
	 */
	public SettingsData(final int nodeIndex) {
		super(nodeIndex,Status.NORMAL, 0);
		setPreferenceValues();
	}


	/**
	 * Full constructor
	 * 
	 * @param nodeIndex
	 * @param status
	 * @param signalStrength
	 * @param universalRemoteOn
	 * @param keyCode1
	 * @param keyCode2
	 * @param keyCode3
	 * @param camResolution
	 * @param gateAlarmOn
	 * @param sonarDistanceThresholdFeet
	 * @param sonarDistanceThresholdInches
	 * @param sonarDelayBetweenTrips
	 * @param irDistanceThresholdFeet
	 * @param irDistanceThresholdInches
	 * @param irDelayBetweenTrips
	 * @param mwSpeedThresholdCyclesPerSecond
	 * @param mwDelayBetweenTrips
	 * @param multiAlarmTripState
	 * @param camSonarPriority
	 * @param camSonarTripPanAngle
	 * @param camSonarTripTiltAngle
	 * @param camPirPriority
	 * @param camPirTripPanAngle
	 * @param camPirTripTiltAngle
	 * @param camMicrowavePriority
	 * @param camMicrowaveTripPanAngle
	 * @param camMicrowaveTripTiltAngle
	 * @param camLaserPriority
	 * @param camLaserTripPanAngle
	 * @param camLaserTripTiltAngle
	 */
	public SettingsData(Integer nodeIndex, Status status, int signalStrength, int universalRemoteOn, int keyCode1, int keyCode2, int keyCode3, int camResolution, int gateAlarmOn, int sonarDistanceThresholdFeet, int sonarDistanceThresholdInches, int sonarDelayBetweenTrips, int irDistanceThresholdFeet,
			int irDistanceThresholdInches, int irDelayBetweenTrips, int mwSpeedThresholdCyclesPerSecond, int mwDelayBetweenTrips, int multiAlarmTripState, int camSonarPriority, int camSonarTripPanAngle, int camSonarTripTiltAngle, int camPirPriority, int camPirTripPanAngle, int camPirTripTiltAngle,
			int camMicrowavePriority, int camMicrowaveTripPanAngle, int camMicrowaveTripTiltAngle, int camLaserPriority, int camLaserTripPanAngle, int camLaserTripTiltAngle) {
		super(nodeIndex, status, signalStrength);
		this.universalRemoteOn = universalRemoteOn;
		this.keyCodes = new KeyCodes(nodeIndex, status, signalStrength, keyCode1, keyCode2, keyCode3);
		this.camResolution = camResolution;
		this.gateAlarmOn = gateAlarmOn;
		this.sonarDistanceThresholdFeet = sonarDistanceThresholdFeet;
		this.sonarDistanceThresholdInches = sonarDistanceThresholdInches;
		this.sonarDelayBetweenTrips = sonarDelayBetweenTrips;
		this.irDistanceThresholdFeet = irDistanceThresholdFeet;
		this.irDistanceThresholdInches = irDistanceThresholdInches;
		this.irDelayBetweenTrips = irDelayBetweenTrips;
		this.mwSpeedThresholdCyclesPerSecond = mwSpeedThresholdCyclesPerSecond;
		this.mwDelayBetweenTrips = mwDelayBetweenTrips;
		this.multiAlarmTripState = multiAlarmTripState;
		this.camSonarPriority = camSonarPriority;
		this.camSonarTripPanAngle = camSonarTripPanAngle;
		this.camSonarTripTiltAngle = camSonarTripTiltAngle;
		this.camPirPriority = camPirPriority;
		this.camPirTripPanAngle = camPirTripPanAngle;
		this.camPirTripTiltAngle = camPirTripTiltAngle;
		this.camMicrowavePriority = camMicrowavePriority;
		this.camMicrowaveTripPanAngle = camMicrowaveTripPanAngle;
		this.camMicrowaveTripTiltAngle = camMicrowaveTripTiltAngle;
		this.camLaserPriority = camLaserPriority;
		this.camLaserTripPanAngle = camLaserTripPanAngle;
		this.camLaserTripTiltAngle = camLaserTripTiltAngle;
	}


	/**
	 * Sets the parameter values from data stored in the preferences
	 * 
	 * @param remoteNodeIndex the index of the remote node
	 */
	public void setPreferenceValues() {
		universalRemoteOn = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.UNIVERSAL_REMOTE_ACCESS_ON, getNodeIndex()));
		keyCodes.setKeyCode1(Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.ACCESS_CODE_1, getNodeIndex())));
		keyCodes.setKeyCode2(Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.ACCESS_CODE_2, getNodeIndex())));
		keyCodes.setKeyCode3(Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.ACCESS_CODE_3, getNodeIndex())));
		camResolution = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_RES, getNodeIndex()));
		gateAlarmOn = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.GATE_ACCESS_ON, getNodeIndex()));
		sonarDistanceThresholdFeet = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.SONAR_DISTANCE_THRES_FEET, getNodeIndex()));
		sonarDistanceThresholdInches = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.SONAR_DISTANCE_THRES_INCHES, getNodeIndex()));
		sonarDelayBetweenTrips = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.SONAR_DELAY_BTWN_TRIPS, getNodeIndex()));
		irDistanceThresholdFeet = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.IR_DISTANCE_THRES_FEET, getNodeIndex()));
		irDistanceThresholdInches = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.IR_DISTANCE_THRES_INCHES, getNodeIndex()));
		irDelayBetweenTrips = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.IR_DELAY_BTWN_TRIPS, getNodeIndex()));
		mwSpeedThresholdCyclesPerSecond = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.MW_SPEED_THRES_CYCLES_PER_SEC, getNodeIndex()));
		mwDelayBetweenTrips = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.MW_DELAY_BTWN_TRIPS, getNodeIndex()));
		multiAlarmTripState = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.MULTI_ALARM_TRIP_STATE, getNodeIndex()));
		camSonarPriority = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_SONAR_TRIP_ANGLE_PRIORITY, getNodeIndex()));
		camSonarTripPanAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_SONAR_TRIP_ANGLE_PAN, getNodeIndex()));
		camSonarTripTiltAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_SONAR_TRIP_ANGLE_TILT, getNodeIndex()));
		camPirPriority = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_PIR_TRIP_ANGLE_PRIORITY, getNodeIndex()));
		camPirTripPanAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_PIR_TRIP_ANGLE_PAN, getNodeIndex()));
		camPirTripTiltAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_PIR_TRIP_ANGLE_TILT, getNodeIndex()));
		camMicrowavePriority = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_MW_TRIP_ANGLE_PRIORITY, getNodeIndex()));
		camMicrowaveTripPanAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_MW_TRIP_ANGLE_PAN, getNodeIndex()));
		camMicrowaveTripTiltAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_MW_TRIP_ANGLE_TILT, getNodeIndex()));
		camLaserPriority = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_MW_TRIP_ANGLE_PRIORITY, getNodeIndex()));
		camLaserTripPanAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_LASER_TRIP_ANGLE_PAN, getNodeIndex()));
		camLaserTripTiltAngle = Integer.parseInt(UGateKeeper.DEFAULT.settingsGet(RemoteSettings.CAM_LASER_TRIP_ANGLE_TILT, getNodeIndex()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final Field[] fields = getClass().getDeclaredFields();
		final StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" [");
		for (final Field field : fields) {
			try {
				sb.append(field.getName());
				sb.append('=');
				sb.append(field.getInt(this));
				sb.append(", ");
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
		sb.append(']');
		return sb.toString();
	}

	/**
	 * @return the list of field values in the order they are declared
	 */
	public int[] toArray() {
		final Field[] fields = getClass().getDeclaredFields();
		final int[] list = new int[fields.length];
		int i = 0;
		for (final Field field : fields) {
			try {
				list[i++] = field.getInt(this);
				//System.out.println("Value: " + list[i - 1]);
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
		return Arrays.copyOf(list, i);
	}
	
	/**
	 * @return the universalRemoteOn
	 */
	public int getUniversalRemoteOn() {
		return universalRemoteOn;
	}

	/**
	 * @return the keyCodes
	 */
	public KeyCodes getKeyCodes() {
		return keyCodes;
	}

	/**
	 * @return the camResolution
	 */
	public int getCamResolution() {
		return camResolution;
	}

	/**
	 * @param camResolution the camResolution to set
	 */
	public void setCamResolution(int camResolution) {
		this.camResolution = camResolution;
	}

	/**
	 * @return the gateAlarmOn
	 */
	public int getGateAlarmOn() {
		return gateAlarmOn;
	}

	/**
	 * @param gateAlarmOn the gateAlarmOn to set
	 */
	public void setGateAlarmOn(int gateAlarmOn) {
		this.gateAlarmOn = gateAlarmOn;
	}

	/**
	 * @return the sonarDistanceThresholdFeet
	 */
	public int getSonarDistanceThresholdFeet() {
		return sonarDistanceThresholdFeet;
	}

	/**
	 * @param sonarDistanceThresholdFeet the sonarDistanceThresholdFeet to set
	 */
	public void setSonarDistanceThresholdFeet(int sonarDistanceThresholdFeet) {
		this.sonarDistanceThresholdFeet = sonarDistanceThresholdFeet;
	}

	/**
	 * @return the sonarDistanceThresholdInches
	 */
	public int getSonarDistanceThresholdInches() {
		return sonarDistanceThresholdInches;
	}

	/**
	 * @param sonarDistanceThresholdInches the sonarDistanceThresholdInches to set
	 */
	public void setSonarDistanceThresholdInches(int sonarDistanceThresholdInches) {
		this.sonarDistanceThresholdInches = sonarDistanceThresholdInches;
	}

	/**
	 * @return the sonarDelayBetweenTrips
	 */
	public int getSonarDelayBetweenTrips() {
		return sonarDelayBetweenTrips;
	}

	/**
	 * @param sonarDelayBetweenTrips the sonarDelayBetweenTrips to set
	 */
	public void setSonarDelayBetweenTrips(int sonarDelayBetweenTrips) {
		this.sonarDelayBetweenTrips = sonarDelayBetweenTrips;
	}

	/**
	 * @return the irDistanceThresholdFeet
	 */
	public int getIrDistanceThresholdFeet() {
		return irDistanceThresholdFeet;
	}

	/**
	 * @param irDistanceThresholdFeet the irDistanceThresholdFeet to set
	 */
	public void setIrDistanceThresholdFeet(int irDistanceThresholdFeet) {
		this.irDistanceThresholdFeet = irDistanceThresholdFeet;
	}

	/**
	 * @return the irDistanceThresholdInches
	 */
	public int getIrDistanceThresholdInches() {
		return irDistanceThresholdInches;
	}

	/**
	 * @param irDistanceThresholdInches the irDistanceThresholdInches to set
	 */
	public void setIrDistanceThresholdInches(int irDistanceThresholdInches) {
		this.irDistanceThresholdInches = irDistanceThresholdInches;
	}

	/**
	 * @return the irDelayBetweenTrips
	 */
	public int getIrDelayBetweenTrips() {
		return irDelayBetweenTrips;
	}

	/**
	 * @param irDelayBetweenTrips the irDelayBetweenTrips to set
	 */
	public void setIrDelayBetweenTrips(int irDelayBetweenTrips) {
		this.irDelayBetweenTrips = irDelayBetweenTrips;
	}

	/**
	 * @return the mwSpeedThresholdCyclesPerSecond
	 */
	public int getMwSpeedThresholdCyclesPerSecond() {
		return mwSpeedThresholdCyclesPerSecond;
	}

	/**
	 * @param mwSpeedThresholdCyclesPerSecond the mwSpeedThresholdCyclesPerSecond to set
	 */
	public void setMwSpeedThresholdCyclesPerSecond(
			int mwSpeedThresholdCyclesPerSecond) {
		this.mwSpeedThresholdCyclesPerSecond = mwSpeedThresholdCyclesPerSecond;
	}

	/**
	 * @return the mwDelayBetweenTrips
	 */
	public int getMwDelayBetweenTrips() {
		return mwDelayBetweenTrips;
	}

	/**
	 * @param mwDelayBetweenTrips the mwDelayBetweenTrips to set
	 */
	public void setMwDelayBetweenTrips(int mwDelayBetweenTrips) {
		this.mwDelayBetweenTrips = mwDelayBetweenTrips;
	}

	/**
	 * @return the multiAlarmTripState
	 */
	public int getMultiAlarmTripState() {
		return multiAlarmTripState;
	}

	/**
	 * @param multiAlarmTripState the multiAlarmTripState to set
	 */
	public void setMultiAlarmTripState(int multiAlarmTripState) {
		this.multiAlarmTripState = multiAlarmTripState;
	}

	/**
	 * @return the camSonarTripPanAngle
	 */
	public int getCamSonarTripPanAngle() {
		return camSonarTripPanAngle;
	}

	/**
	 * @param camSonarTripPanAngle the camSonarTripPanAngle to set
	 */
	public void setCamSonarTripPanAngle(int camSonarTripPanAngle) {
		this.camSonarTripPanAngle = camSonarTripPanAngle;
	}

	/**
	 * @return the camSonarTripTiltAngle
	 */
	public int getCamSonarTripTiltAngle() {
		return camSonarTripTiltAngle;
	}

	/**
	 * @param camSonarTripTiltAngle the camSonarTripTiltAngle to set
	 */
	public void setCamSonarTripTiltAngle(int camSonarTripTiltAngle) {
		this.camSonarTripTiltAngle = camSonarTripTiltAngle;
	}

	/**
	 * @return the camIrTripPanAngle
	 */
	public int getCamIrTripPanAngle() {
		return camPirTripPanAngle;
	}

	/**
	 * @param camIrTripPanAngle the camIrTripPanAngle to set
	 */
	public void setCamIrTripPanAngle(int camIrTripPanAngle) {
		this.camPirTripPanAngle = camIrTripPanAngle;
	}

	/**
	 * @return the camIrTripTiltAngle
	 */
	public int getCamIrTripTiltAngle() {
		return camPirTripTiltAngle;
	}

	/**
	 * @param camIrTripTiltAngle the camIrTripTiltAngle to set
	 */
	public void setCamIrTripTiltAngle(int camIrTripTiltAngle) {
		this.camPirTripTiltAngle = camIrTripTiltAngle;
	}

	/**
	 * @return the camMicrowaveTripPanAngle
	 */
	public int getCamMicrowaveTripPanAngle() {
		return camMicrowaveTripPanAngle;
	}

	/**
	 * @param camMicrowaveTripPanAngle the camMicrowaveTripPanAngle to set
	 */
	public void setCamMicrowaveTripPanAngle(int camMicrowaveTripPanAngle) {
		this.camMicrowaveTripPanAngle = camMicrowaveTripPanAngle;
	}

	/**
	 * @return the camMicrowaveTripTiltAngle
	 */
	public int getCamMicrowaveTripTiltAngle() {
		return camMicrowaveTripTiltAngle;
	}

	/**
	 * @param camMicrowaveTripTiltAngle the camMicrowaveTripTiltAngle to set
	 */
	public void setCamMicrowaveTripTiltAngle(int camMicrowaveTripTiltAngle) {
		this.camMicrowaveTripTiltAngle = camMicrowaveTripTiltAngle;
	}


	/**
	 * @return the camSonarPriority
	 */
	public int getCamSonarPriority() {
		return camSonarPriority;
	}


	/**
	 * @param camSonarPriority the camSonarPriority to set
	 */
	public void setCamSonarPriority(int camSonarPriority) {
		this.camSonarPriority = camSonarPriority;
	}


	/**
	 * @return the camPirPriority
	 */
	public int getCamPirPriority() {
		return camPirPriority;
	}


	/**
	 * @param camPirPriority the camPirPriority to set
	 */
	public void setCamPirPriority(int camPirPriority) {
		this.camPirPriority = camPirPriority;
	}


	/**
	 * @return the camPirTripPanAngle
	 */
	public int getCamPirTripPanAngle() {
		return camPirTripPanAngle;
	}


	/**
	 * @param camPirTripPanAngle the camPirTripPanAngle to set
	 */
	public void setCamPirTripPanAngle(int camPirTripPanAngle) {
		this.camPirTripPanAngle = camPirTripPanAngle;
	}


	/**
	 * @return the camPirTripTiltAngle
	 */
	public int getCamPirTripTiltAngle() {
		return camPirTripTiltAngle;
	}


	/**
	 * @param camPirTripTiltAngle the camPirTripTiltAngle to set
	 */
	public void setCamPirTripTiltAngle(int camPirTripTiltAngle) {
		this.camPirTripTiltAngle = camPirTripTiltAngle;
	}


	/**
	 * @return the camMicrowavePriority
	 */
	public int getCamMicrowavePriority() {
		return camMicrowavePriority;
	}


	/**
	 * @param camMicrowavePriority the camMicrowavePriority to set
	 */
	public void setCamMicrowavePriority(int camMicrowavePriority) {
		this.camMicrowavePriority = camMicrowavePriority;
	}


	/**
	 * @return the camLaserPriority
	 */
	public int getCamLaserPriority() {
		return camLaserPriority;
	}


	/**
	 * @param camLaserPriority the camLaserPriority to set
	 */
	public void setCamLaserPriority(int camLaserPriority) {
		this.camLaserPriority = camLaserPriority;
	}


	/**
	 * @return the camLaserTripPanAngle
	 */
	public int getCamLaserTripPanAngle() {
		return camLaserTripPanAngle;
	}


	/**
	 * @param camLaserTripPanAngle the camLaserTripPanAngle to set
	 */
	public void setCamLaserTripPanAngle(int camLaserTripPanAngle) {
		this.camLaserTripPanAngle = camLaserTripPanAngle;
	}


	/**
	 * @return the camLaserTripTiltAngle
	 */
	public int getCamLaserTripTiltAngle() {
		return camLaserTripTiltAngle;
	}


	/**
	 * @param camLaserTripTiltAngle the camLaserTripTiltAngle to set
	 */
	public void setCamLaserTripTiltAngle(int camLaserTripTiltAngle) {
		this.camLaserTripTiltAngle = camLaserTripTiltAngle;
	}
}
