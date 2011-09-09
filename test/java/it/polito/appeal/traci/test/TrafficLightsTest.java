/*   
    Copyright (C) 2011 ApPeAL Group, Politecnico di Torino

    This file is part of TraCI4J.

    TraCI4J is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    TraCI4J is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with TraCI4J.  If not, see <http://www.gnu.org/licenses/>.
*/

package it.polito.appeal.traci.test;

import static org.junit.Assert.*;
import it.polito.appeal.traci.ReadObjectVarQuery;
import it.polito.appeal.traci.Repository;
import it.polito.appeal.traci.SumoTraciConnection;
import it.polito.appeal.traci.TrafficLight;
import it.polito.appeal.traci.TrafficLight.ChangeLightsStateQuery;
import it.polito.appeal.traci.TrafficLight.ControlledLink;
import it.polito.appeal.traci.TrafficLight.ControlledLinks;
import it.polito.appeal.traci.TrafficLight.LightState;
import it.polito.appeal.traci.TrafficLight.Logic;
import it.polito.appeal.traci.TrafficLight.Phase;
import it.polito.appeal.traci.TrafficLight.Program;
import it.polito.appeal.traci.TrafficLight.TLState;

import java.io.IOException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrafficLightsTest {

	private static final String SIM_CONFIG_LOCATION = "test/sumo_maps/cross3ltl/test.sumo.cfg";
	private Repository<TrafficLight> repo;
	private SumoTraciConnection conn;

	
	@Before
	public void setUp() throws Exception {
		TraCITest.printSeparator();
		conn = TraCITest.startSumoConn(SIM_CONFIG_LOCATION);
		repo = conn.getTrafficLightRepository();
	}

	@After
	public void tearDown() throws IOException, InterruptedException {
		TraCITest.stopSumoConn(conn);
	}
	
	@Test
	public void testTrafficLightExistence() throws IOException {
		assertNotNull(repo.getByID("0"));
	}
	
	private static final LightState[][] PHASES = new LightState[][] {
		// phase 0
		new LightState[] {
				LightState.GREEN_NODECEL, 
				LightState.GREEN_NODECEL, 
				LightState.GREEN,         
				LightState.GREEN,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.GREEN_NODECEL, 
				LightState.GREEN_NODECEL, 
				LightState.GREEN,         
				LightState.GREEN,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
		},
		// phase 1
		new LightState[] {
				LightState.YELLOW, 
				LightState.YELLOW, 
				LightState.GREEN,         
				LightState.GREEN,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.YELLOW, 
				LightState.YELLOW, 
				LightState.GREEN,         
				LightState.GREEN,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
		},
		// phase 2	
		new LightState[] {
				LightState.RED, 
				LightState.RED, 
				LightState.GREEN_NODECEL,         
				LightState.GREEN_NODECEL,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED, 
				LightState.RED, 
				LightState.GREEN_NODECEL,         
				LightState.GREEN_NODECEL,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
		},
		// phase 3	
		new LightState[] {
				LightState.RED, 
				LightState.RED, 
				LightState.YELLOW,         
				LightState.YELLOW,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED, 
				LightState.RED, 
				LightState.YELLOW,         
				LightState.YELLOW,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
		},
		// phase 4
		new LightState[] {
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.GREEN_NODECEL, 
				LightState.GREEN_NODECEL, 
				LightState.GREEN,         
				LightState.GREEN,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.GREEN_NODECEL, 
				LightState.GREEN_NODECEL, 
				LightState.GREEN,         
				LightState.GREEN,         
		},
		// phase 5
		new LightState[] {
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.YELLOW, 
				LightState.YELLOW, 
				LightState.GREEN,         
				LightState.GREEN,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.YELLOW, 
				LightState.YELLOW, 
				LightState.GREEN,         
				LightState.GREEN,         
		},
		// phase 6	
		new LightState[] {
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED, 
				LightState.RED, 
				LightState.GREEN_NODECEL,         
				LightState.GREEN_NODECEL,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED, 
				LightState.RED, 
				LightState.GREEN_NODECEL,         
				LightState.GREEN_NODECEL,         
		},
		// phase 7	
		new LightState[] {
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED, 
				LightState.RED, 
				LightState.YELLOW,         
				LightState.YELLOW,         
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED,           
				LightState.RED, 
				LightState.RED, 
				LightState.YELLOW,         
				LightState.YELLOW,         
		},		
	};
	
	private static final int[] PHASES_DURATION = new int[] {
		31, 4, 6, 4, 31, 4, 6, 4
	};

	
	@Test
	public void testStateAtFirstStep() throws IOException {
		TrafficLight tl = repo.getByID("0");
		TLState tlState = tl.getReadCurrentStateQuery().get();
		final LightState[] states = tlState.lightStates;
		assertEquals(16, states.length);
		assertArrayEquals(PHASES[0], states);
	}
	
	@Test
	public void testStateUpdate() throws IOException {
		TrafficLight tl = repo.getByID("0");
		final ReadObjectVarQuery<TLState> query = tl.getReadCurrentStateQuery();
		
		// looks like SUMO shifts all TL timings ahead one second
		conn.nextSimStep();
		
		for (int p = 0; p < PHASES.length; p++) {
			TLState tlState = query.get();
			final LightState[] states = tlState.lightStates;
			System.out.println("state at t=" + conn.getCurrentSimStep() + "\n" 
					+ "  expected " + Arrays.toString(PHASES[p]) + "\n"
					+ "  actual   " + Arrays.toString(states));
			assertArrayEquals("state at t=" + conn.getCurrentSimStep(), PHASES[p], states);
			
			for (int t=0; t<PHASES_DURATION[p]; t++)
				conn.nextSimStep();
		}
	}
	
	@Test
	public void testCurrentDuration() throws IOException {
		TrafficLight tl = repo.getByID("0");
		final ReadObjectVarQuery<Integer> query = tl.getReadCurrentPhaseDurationQuery();

		// looks like SUMO shifts all TL timings ahead one second
		conn.nextSimStep();
		
		for (int p = 0; p < PHASES.length; p++) {
			int phaseDuration = query.get();
			assertEquals(PHASES_DURATION[p], phaseDuration / 1000);
			
			for (int t=0; t<PHASES_DURATION[p]; t++)
				conn.nextSimStep();
		}
	}
	
	private static final String[][] linksLaneIDs = new String[][] {
		new String[] { "4si_0", ":0_0_0", "1o_0" },
		new String[] { "4si_1", ":0_1_0", "3o_0" },
		new String[] { "4si_2", ":0_2_0", "2o_0" },
		new String[] { "4si_2", ":0_3_0", "4o_0" },
		new String[] { "2si_0", ":0_4_0", "4o_0" },
		new String[] { "2si_1", ":0_5_0", "1o_0" },
		new String[] { "2si_2", ":0_6_0", "3o_0" },
		new String[] { "2si_2", ":0_7_0", "2o_0" },
		new String[] { "3si_0", ":0_8_0", "2o_0" },
		new String[] { "3si_1", ":0_9_0", "4o_0" },
		new String[] { "3si_2", ":0_10_0", "1o_0" },
		new String[] { "3si_2", ":0_11_0", "3o_0" },
		new String[] { "1si_0", ":0_12_0", "3o_0" },
		new String[] { "1si_1", ":0_13_0", "2o_0" },
		new String[] { "1si_2", ":0_14_0", "4o_0" },
		new String[] { "1si_2", ":0_15_0", "1o_0" },
	};
	
	@Test
	public void testControlledLinks() throws IOException {
		TrafficLight tl = repo.getByID("0");
		ControlledLinks links = tl.getReadControlledLinksQuery().get();
		
		assertEquals(linksLaneIDs.length, links.getLinks().length);
		for (int i=0; i<linksLaneIDs.length; i++) {
			ControlledLink[] linksForSignal = links.getLinks()[i];
			assertEquals(1, linksForSignal.length);
			ControlledLink link = linksForSignal[0];
			assertEquals(linksLaneIDs[i][0], link.getIncomingLane().getID());
			assertEquals(linksLaneIDs[i][1], link.getAcrossLane().getID());
			assertEquals(linksLaneIDs[i][2], link.getOutgoingLane().getID());
		}
	}
	
	@Test
	public void testCompleteProgramDefinition() throws IOException {
		TrafficLight tl = repo.getByID("0");
		Program program = tl.getCompleteDefinitionQuery().get();
		
		assertEquals(1, program.getLogics().length);
		Logic logic = program.getLogics()[0];
		
		assertEquals("0", logic.getSubID());
		assertEquals(0, logic.getCurrentPhaseIndex());
		
		Phase[] phases = logic.getPhases();
		assertEquals(8, phases.length);
		
		for (int i=0; i<phases.length; i++) {
			Phase ph = phases[i];
			assertEquals(PHASES_DURATION[i] * 1000, ph.getDuration());
			assertArrayEquals(PHASES[i], ph.getState().lightStates);
		}
	}
	
	private static final TLState TEST_TL_STATE = new TLState("rrGGyyyyggrryryr");

	@Test
	public void testChangeState() throws IOException {
		TrafficLight tl = repo.getByID("0");
		ChangeLightsStateQuery q = tl.getChangeLightsStateQuery();
		q.setValue(TEST_TL_STATE);
		q.run();
		
		assertEquals(TEST_TL_STATE, tl.getReadCurrentStateQuery().get());
	}
}