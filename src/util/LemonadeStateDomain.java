package util;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import agent.Agent;
import agent.QLearningAgent;

/**
 * @author aladdinagentschool
 * subclass of generic type statedomain, can be implemented. It implements all the possible lemonade states
 */

public class LemonadeStateDomain extends StateDomain<JointActionState> {  
	protected Set<JointActionState> states;
	
	// creates the domain
	public void Init (){
		int player =5;
		Vector<Agent> agentSet = new Vector <Agent>();
		states = new LinkedHashSet <JointActionState>();
		Map <Agent , Action> newState;
		newState = new HashMap <Agent, Action>();
		Action One = new EnumActions (1);
		Action Two = new EnumActions (2); Action Three = new EnumActions (3);Action Four = new EnumActions (4); Action Five = new EnumActions (5);
		Action Six = new EnumActions (6); Action Seven = new EnumActions (7); Action Eight = new EnumActions (8); Action Nine = new EnumActions (9); Action Ten = new EnumActions (10);
		Action Eleven = new EnumActions (11); Action Twelve = new EnumActions (12);
		for (int i=0; i<player; i++){
			Agent agent = new QLearningAgent();
			agentSet.add(agent);
		}
		
		for (Action One1 : One.actionsSet()){
			for(Action Two2:Two.actionsSet()){
				for (Action Three3 : Three.actionsSet()){
					for (Action Four4 : Four.actionsSet()){
						for (Action Five5 : Five.actionsSet()){
							for (Action Six6 : Six.actionsSet()){
								for (Action Seven7 : Seven.actionsSet()){
									for (Action Eight8 : Eight.actionsSet()){
										for (Action Nine9 : Nine.actionsSet()){
											for (Action Ten10 : Ten.actionsSet()){
												for (Action Eleven11 : Eleven.actionsSet()){
													for (Action Twelve12 : Twelve.actionsSet()){
												
														JointActionState NewState = new JointActionState ();
															if (One1.getName()!= "One" && Two2.getName()!= "Two" && Three3.getName() != "Three" && Four4.getName()!= "Four" && Five5.getName() != "Five" && Six6.getName()!= "Six" && Seven7.getName() != "Seven" && Eight8.getName()!= "Eight" && Three3.getName() != "Three" && Two2.getName()!= "Two" && Nine.getName() != "Nine" && Ten10.getName()!= "Ten" && Eleven11.getName() != "Eleven" && Twelve12.getName()!= "Twelve"){
																newState.put(agentSet.get(player), One1);newState.put(agentSet.get(player), Two2);newState.put(agentSet.get(player), Three3);newState.put(agentSet.get(player), Four4);newState.put(agentSet.get(player), Five5);newState.put(agentSet.get(player), Six6);newState.put(agentSet.get(player), Seven7);newState.put(agentSet.get(player), Eight8);newState.put(agentSet.get(player), Nine9);newState.put(agentSet.get(player), Ten10);newState.put(agentSet.get(player), Eleven11);newState.put(agentSet.get(player), Twelve12);
																
																
																NewState.addFeature(newState); //NewState.addFeature(Two2); NewState.addFeature(Three3);NewState.addFeature(Four4);NewState.addFeature(Five5);NewState.addFeature(Six6);NewState.addFeature(Seven7); NewState.addFeature(Eight8); NewState.addFeature(Nine9); NewState.addFeature(Ten10); NewState.addFeature(Eleven11); NewState.addFeature(Twelve12);
																states.add(NewState);
															}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	
	}
	
	// adds state to the domain
	public void add (JointActionState state){
		states.add(state);
	}
	
	// to get lemonade state domain
	public Set<JointActionState >  getStateDomain(){
		return states;
		}
	
	// returns size of domain
	public int size(){
		return states.size();
		
	}
}


	
		
	
