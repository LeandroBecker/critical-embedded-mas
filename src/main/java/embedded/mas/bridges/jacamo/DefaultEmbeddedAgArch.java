package embedded.mas.bridges.jacamo;

// import java.util.ArrayList;
// import java.util.Collection;
import java.util.*;

import embedded.mas.exception.PerceivingException;
import jason.architecture.AgArch;
import jason.asSyntax.Literal;
import static jason.asSyntax.ASSyntax.createLiteral;


public class DefaultEmbeddedAgArch extends AgArch{


	protected Collection <DefaultDevice> devices = null;

	//private int lastCPval = -12345; //LBB: this is temporary, requires a better implementation

	public DefaultEmbeddedAgArch() {		
		super();

	}

	// @Override
	// public Boolean[] perceiveCBS() { //v0: Still not generic, works only for the hand-coded perception "value2"
	// 	Boolean[] percepts = new Boolean[8];
	// 	for(int i=0; i<8; i++)
	// 		percepts[i] = Boolean.FALSE;
		
	// 	for(IDevice s:this.devices) { //for each sensor
	// 		try {
	// 			Collection<Literal> p = s.getPercepts(); //List<Literal> beliefs = microcontroller.read(); return beliefs;
	// 			if(p!=null) {
	// 				for(Literal l:p) {
	// 					String functor = l.getFunctor();
	// 					if(functor.contains("critical_percept")){
	// 						int value = Integer.parseInt(l.getTerm(0).toString());
	// 						if(value != lastCPval){
	// 							lastCPval = value;
	// 							percepts[0] = Boolean.TRUE;
	// 						}
	// 						// else
	// 						// 	percepts[0] = Boolean.FALSE;
	// 					}
							
	// 				}
	// 			}
	// 		} catch (PerceivingException e) {} //if it fails, do nothing 			
	// 	}
	// 	return percepts;
   	// }
  
	@Override
	public void stop() {
		// System.out.println("Critical messages: "+msgCount);
		// int t = 0;
		// for (int v: msgCount.values())
		// 	t += v;
		// System.out.println("Total sent messages: "+t);

		// System.out.println("Actions: "+actCount);
		// t = 0;
		// for (int v: actCount.values())
		// 	t += v;
		// System.out.println("Total actions: "+t);
	}

	@Override
	public Collection<Literal> perceive() {
		Collection<Literal> p = super.perceive(); //get the default perceptions
		Collection<Literal> sensorData = null;
		if(devices!=null)
			sensorData = updateSensor(); //get the sensor data	
		
		if(p!=null&&sensorData!=null) //attach the sensor data in the default perception list
			p.addAll(sensorData);
		else
			if(sensorData!=null)
				p = sensorData;

		//LBB: code to test if the critical perception is observed
		// if(sensorData!=null){
		// 	Literal elementToSearch = Literal.parseLiteral("value2");
		// 	boolean found = sensorData.contains(elementToSearch);
		// 	Integer c = msgCount.get("v2");
		// 	if (c == null)
		// 		c = 0;
		// 	msgCount.put("v2",c+1);
		// }

		return p;
	}


	public void setDevices(Collection<DefaultDevice> devices) {
		this.devices = devices;
	}


	public Collection<DefaultDevice> getDevices(){
		return this.devices;

	}


	private final Collection<Literal> updateSensor() {
		/* Same comment as in EmbeddeAgent.checkSensor
		 * The architecture requres a list of devices to handle the perceptions. 
		   In some point after the agent creation, an architecture other than DefaultEmbeddedAgArch is set and the list of sensor is lost.
		   This method update the list of devices if it is null.
		   TODO: improve this */
		ArrayList<Literal> percepts = new ArrayList<Literal>();
		synchronized (this.devices) {

			if(this.devices==null) return null;
			//*******************
			
			for(IDevice s:this.devices) { //for each sensor
				try {
					Collection<Literal> p = s.getPercepts();
					if(p!=null) {
						for(Literal l:p) {
							l.addAnnot(createLiteral("device", s.getId()));
						}

						percepts.addAll(p);//get all the sensor data
					}
				} catch (PerceivingException e) {} //if it fails, do nothing 			
			}
		}
		if(percepts.size()==0) return null;
		return percepts;
	}

}
