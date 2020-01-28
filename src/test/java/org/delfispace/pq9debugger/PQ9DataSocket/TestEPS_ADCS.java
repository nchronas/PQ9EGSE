/*
 * Copyright (C) 2018 Stefano Speretta
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.delfispace.pq9debugger.PQ9DataSocket;

import java.io.IOException;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Stefano Speretta <s.speretta@tudelft.nl>
 */
public class TestEPS_ADCS
{
    private final static int TIMEOUT = 300; // in ms
    private final static StatisticsGenerator[] STATS = new StatisticsGenerator[31];
    
    public static void main(String[] args) throws IOException, InterruptedException, ParseException 
    {
        STATS[0] = new StatisticsGenerator();
        STATS[1] = new StatisticsGenerator();
        STATS[2] = new StatisticsGenerator();
        STATS[3] = new StatisticsGenerator();
        STATS[4] = new StatisticsGenerator();
        STATS[5] = new StatisticsGenerator();
        STATS[6] = new StatisticsGenerator();
        STATS[7] = new StatisticsGenerator();
        STATS[8] = new StatisticsGenerator();
        STATS[9] = new StatisticsGenerator();
        STATS[10] = new StatisticsGenerator();
        STATS[11] = new StatisticsGenerator();
        STATS[12] = new StatisticsGenerator();
        STATS[13] = new StatisticsGenerator();
        STATS[14] = new StatisticsGenerator();
        STATS[15] = new StatisticsGenerator();
        STATS[16] = new StatisticsGenerator();
        STATS[17] = new StatisticsGenerator();
        STATS[18] = new StatisticsGenerator();
        STATS[19] = new StatisticsGenerator();
        STATS[20] = new StatisticsGenerator();
        STATS[21] = new StatisticsGenerator();
        
        STATS[22] = new StatisticsGenerator();
        STATS[23] = new StatisticsGenerator();
        STATS[24] = new StatisticsGenerator();
        STATS[25] = new StatisticsGenerator();
        STATS[26] = new StatisticsGenerator();
        STATS[27] = new StatisticsGenerator();
        STATS[28] = new StatisticsGenerator();
        STATS[29] = new StatisticsGenerator();
        STATS[30] = new StatisticsGenerator();

        
        try (PQ9DataClient client = new PQ9DataClient("localhost", 10000)) 
        {
            client.setTimeout(TIMEOUT);
            
            JSONObject command1 = new JSONObject();
            command1.put("_send_", "GetTelemetry");
            command1.put("Destination", "EPS");
            
            JSONObject command2 = new JSONObject();
            command2.put("_send_", "GetTelemetry");
            command2.put("Destination", "ADCS");
            
            int transmitted = 800000;
            
            for (int h = 0; h < transmitted; h++) 
            {
                if ((h % 1000) == 0)
                {
                    System.out.println(h);
                }
                Date before = new Date();
                if ((h % 2) == 0)
                {
                    client.sendFrame(command1);
                }
                else
                {
                    client.sendFrame(command2);
                }
                
                try 
                {
                    JSONObject reply = client.getFrame();
                    
                    Date after = new Date();
                    long delta = after.getTime() - before.getTime();
                    STATS[0].addPoint(delta);
                    
                    if ((h % 2) == 0)
                    {
                        if (reply.get("EPS_DC_INA_Status").toString().equals("Working"))
                        {
                            STATS[1].addPoint(Double.parseDouble(reply.get("IntVoltage").toString()));
                            STATS[2].addPoint(Double.parseDouble(reply.get("IntCurrent").toString()));
                        }

                        if (reply.get("EPS_UR_INA_Status").toString().equals("Working"))
                        {
                            STATS[3].addPoint(Double.parseDouble(reply.get("URBVoltage").toString()));
                            STATS[4].addPoint(Double.parseDouble(reply.get("URBCurrent").toString()));
                        }                    

                        // bus 1 is working...
                        if (reply.get("EPS_B1_INA_Status").toString().equals("Working"))
                        {
                            STATS[5].addPoint(Double.parseDouble(reply.get("B1_voltage").toString()));
                            STATS[6].addPoint(Double.parseDouble(reply.get("B1_current").toString()));
                        }

                        // bus 2 is working...
                        if (reply.get("EPS_B2_INA_Status").toString().equals("Working"))
                        {
                            STATS[7].addPoint(Double.parseDouble(reply.get("B2_voltage").toString()));
                            STATS[8].addPoint(Double.parseDouble(reply.get("B2_current").toString()));
                        }

                        // bus 3 is working...
                        if (reply.get("EPS_B3_INA_Status").toString().equals("Working"))
                        {
                            STATS[9].addPoint(Double.parseDouble(reply.get("B3_voltage").toString()));
                            STATS[10].addPoint(Double.parseDouble(reply.get("B3_current").toString()));
                        }

                        // bus 4 is working...
                        if (reply.get("EPS_B4_INA_Status").toString().equals("Working"))
                        {
                            STATS[11].addPoint(Double.parseDouble(reply.get("B4_voltage").toString()));
                            STATS[12].addPoint(Double.parseDouble(reply.get("B4_current").toString()));
                        }

                        // solar array Yp is working...
                        if (reply.get("SA_YP_INA_Status").toString().equals("Working"))
                        {
                            STATS[13].addPoint(Double.parseDouble(reply.get("SA_YP_voltage").toString()));
                            STATS[14].addPoint(Double.parseDouble(reply.get("SA_YP_current").toString()));
                        }

                        // solar array Ym is working...
                        if (reply.get("SA_YM_INA_Status").toString().equals("Working"))
                        {
                            STATS[15].addPoint(Double.parseDouble(reply.get("SA_YM_voltage").toString()));
                            STATS[16].addPoint(Double.parseDouble(reply.get("SA_YM_current").toString()));
                        }

                        // solar array Xp is working...
                        if (reply.get("SA_XP_INA_Status").toString().equals("Working"))
                        {
                            STATS[17].addPoint(Double.parseDouble(reply.get("SA_XP_voltage").toString()));
                            STATS[18].addPoint(Double.parseDouble(reply.get("SA_XP_current").toString()));
                        }

                        // solar array Xm is working...
                        if (reply.get("SA_XM_INA_Status").toString().equals("Working"))
                        {
                            STATS[19].addPoint(Double.parseDouble(reply.get("SA_XM_voltage").toString()));
                            STATS[20].addPoint(Double.parseDouble(reply.get("SA_XM_current").toString()));
                        }

                        // battery is working...
                        if (reply.get("EPS_LTC_Status").toString().equals("Working"))
                        {
                            STATS[21].addPoint(Double.parseDouble(reply.get("BattVoltage").toString()));
                        }
                    }
                    else
                    {
                        if (reply.get("TemperatureStatus").toString().equals("Working"))
                        {
                            STATS[22].addPoint(Double.parseDouble(reply.get("Temperature").toString()));
                        }

                        // main bus is working
                        if (reply.get("BusStatus").toString().equals("Working"))
                        {
                            STATS[23].addPoint(Double.parseDouble(reply.get("BusVoltage").toString()));
                            STATS[24].addPoint(Double.parseDouble(reply.get("BusCurrent").toString()));
                        }                    

                        // Torquer X is working...
                        if (reply.get("TorquerXStatus").toString().equals("Working"))
                        {
                            STATS[25].addPoint(Double.parseDouble(reply.get("TorquerXVoltage").toString()));
                            STATS[26].addPoint(Double.parseDouble(reply.get("TorquerXCurrent").toString()));
                        }

                        // Torquer Y is working...
                        if (reply.get("TorquerYStatus").toString().equals("Working"))
                        {
                            STATS[27].addPoint(Double.parseDouble(reply.get("TorquerYVoltage").toString()));
                            STATS[28].addPoint(Double.parseDouble(reply.get("TorquerYCurrent").toString()));
                        }

                        // Torquer Z is working...
                        if (reply.get("TorquerZStatus").toString().equals("Working"))
                        {
                            STATS[29].addPoint(Double.parseDouble(reply.get("TorquerZVoltage").toString()));
                            STATS[30].addPoint(Double.parseDouble(reply.get("TorquerZCurrent").toString()));
                        }  
                    }                
                } catch (TimeoutException ex) 
                {
                    // nothing to do here
                }                                                   
            }           
            
            System.out.println();
            System.out.println("Transmitted: " + transmitted);
            STATS[0].printStatistics();
            
            System.out.println();
            System.out.println("IntVoltage:");
            STATS[1].printStatistics();
            
            System.out.println();
            System.out.println("IntCurrent:");
            STATS[2].printStatistics();
            
            System.out.println();
            System.out.println("URBVoltage:");
            STATS[3].printStatistics();
            
            System.out.println();
            System.out.println("URBCurrent:");
            STATS[4].printStatistics();            
            
            System.out.println();
            System.out.println("B1Voltage:");
            STATS[5].printStatistics();
            
            System.out.println();
            System.out.println("B1Current:");
            STATS[6].printStatistics();
            
            System.out.println();
            System.out.println("B2Voltage:");
            STATS[7].printStatistics();
            
            System.out.println();
            System.out.println("B2Current:");
            STATS[8].printStatistics();
            
            System.out.println();
            System.out.println("B3Voltage:");
            STATS[9].printStatistics();
            
            System.out.println();
            System.out.println("B3Current:");
            STATS[10].printStatistics();
            
            System.out.println();
            System.out.println("B4Voltage:");
            STATS[11].printStatistics();
            
            System.out.println();
            System.out.println("B4Current:");
            STATS[12].printStatistics();
            
            System.out.println();
            System.out.println("SAYpVoltage:");
            STATS[13].printStatistics();
            
            System.out.println();
            System.out.println("SAYpCurrent:");
            STATS[14].printStatistics();
            
            System.out.println();
            System.out.println("SAYmVoltage:");
            STATS[15].printStatistics();
            
            System.out.println();
            System.out.println("SAYmCurrent:");
            STATS[16].printStatistics();
            
            System.out.println();
            System.out.println("SAXpVoltage:");
            STATS[17].printStatistics();
            
            System.out.println();
            System.out.println("SAXpCurrent:");
            STATS[18].printStatistics();
            
            System.out.println();
            System.out.println("SAXmVoltage:");
            STATS[19].printStatistics();
            
            System.out.println();
            System.out.println("SAXmCurrent:");
            STATS[20].printStatistics();
            
            System.out.println();
            System.out.println("BattVoltage:");
            STATS[21].printStatistics();
            
            System.out.println();
            System.out.println("Temperature:");
            STATS[22].printStatistics();
            
            System.out.println();
            System.out.println("BusVoltage:");
            STATS[23].printStatistics();
            
            System.out.println();
            System.out.println("BusCurrent:");
            STATS[24].printStatistics();
            
            System.out.println();
            System.out.println("TorquerXVoltage:");
            STATS[25].printStatistics();            
            
            System.out.println();
            System.out.println("TorquerXCurrent:");
            STATS[26].printStatistics();
            
            System.out.println();
            System.out.println("TorquerYVoltage:");
            STATS[27].printStatistics();            
            
            System.out.println();
            System.out.println("TorquerXYCurrent:");
            STATS[28].printStatistics();
            
            System.out.println();
            System.out.println("TorquerZVoltage:");
            STATS[29].printStatistics();            
            
            System.out.println();
            System.out.println("TorquerZCurrent:");
            STATS[30].printStatistics();
        }
    }
}
