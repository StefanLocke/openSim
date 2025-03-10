/*******************************************************************************
 * openDLX - A DLX/MIPS processor simulator.
 * Copyright (C) 2013 The openDLX project, University of Augsburg, Germany
 * Project URL: <https://sourceforge.net/projects/opendlx>
 * Development branch: <https://github.com/smetzlaff/openDLX>
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, see <LICENSE>. If not, see
 * <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package openSim.gui.internalframes.factories;

import java.util.ArrayList;
import java.util.Hashtable;

import openSim.gui.MainFrame;
import openSim.gui.command.systemLevel.CommandLoadFrameConfigurationSysLevel;
import openSim.gui.internalframes.concreteframes.CacheFrame;
import openSim.gui.internalframes.concreteframes.ClockCycleFrame;
import openSim.gui.internalframes.concreteframes.CodeFrame;
import openSim.gui.internalframes.concreteframes.LogFrame;
import openSim.gui.internalframes.concreteframes.MemoryFrame;
import openSim.gui.internalframes.concreteframes.RegisterFrame;
import openSim.gui.internalframes.concreteframes.StatisticsFrame;
import openSim.gui.internalframes.concreteframes.editor.EditorFrame;

public class InternalFrameFactory
{
    //here you find every frames title -> preferences depend on names

    private static final Hashtable<Class<?>, String> frameNames = new Hashtable<Class<?>, String>();
    private static InternalFrameFactory instance = null;
    
    private static final String FRAME_NAME_EDITOR = "coding frame";
    private static final String FRAME_NAME_MEMORY = "memory";
    private static final String FRAME_NAME_REGSET = "register set";
    private static final String FRAME_NAME_CODE = "code";
    private static final String FRAME_NAME_STATS = "statistics";
    private static final String FRAME_NAME_LOG = "log";
    private static final String FRAME_NAME_CLOCKCYCLE = "cycles and pipeline";
    private static final String FRAME_NAME_CACHE = "Cache";
    private MainFrame mf;

    static
    {
        //add here new frames
        frameNames.put(EditorFrame.class, FRAME_NAME_EDITOR);
        frameNames.put(MemoryFrame.class, FRAME_NAME_MEMORY);
        frameNames.put(RegisterFrame.class, FRAME_NAME_REGSET);
        frameNames.put(CodeFrame.class, FRAME_NAME_CODE);
        frameNames.put(StatisticsFrame.class, FRAME_NAME_STATS);
        frameNames.put(LogFrame.class, FRAME_NAME_LOG);
        frameNames.put(ClockCycleFrame.class, FRAME_NAME_CLOCKCYCLE);
        frameNames.put(CacheFrame.class, FRAME_NAME_CACHE);
    }

    public static InternalFrameFactory getInstance()
    {
    	// FIXME use lazy thread-safe singleton creation 
        if (instance == null)
        {
            instance = new InternalFrameFactory();
        }
        return instance;
    }

    private InternalFrameFactory()
    {
        mf = MainFrame.getInstance();
    }

    public void createAllFrames(){
        createAllFrames(new String[]{});
    }

    public void createAllFrames(String[] intFrameOrder)
    {
        ArrayList<String> frameOrder = new ArrayList<>(frameNames.size());
        for (String s : intFrameOrder)
            frameOrder.add(s);
        for (String s : frameNames.values())
            if (!frameOrder.contains(s))
                frameOrder.add(s);

        for (String s : frameOrder)
        {
            if (s.equals(FRAME_NAME_REGSET))
                createRegisterFrame();
            else if (s.equals(FRAME_NAME_CODE))
                createCodeFrame();
            else if (s.equals(FRAME_NAME_LOG))
                createLogFrame();
            else if (s.equals(FRAME_NAME_STATS))
                createStatisticsFrame();
            else if (s.equals(FRAME_NAME_MEMORY))
                createMemoryFrame(mf);
            else if (s.equals(FRAME_NAME_CLOCKCYCLE))
                createClockCycleFrame();
            else if (s.equals(FRAME_NAME_CACHE))
                createCacheFrame();
        }

        new CommandLoadFrameConfigurationSysLevel(mf).execute();
    }

    public static String getFrameName(Class<?> c)
    {
        return frameNames.get(c);
    }

    public void createMemoryFrame(MainFrame mf)
    {
        MemoryFrame f = new MemoryFrame(frameNames.get(MemoryFrame.class),mf);
        mf.addInternalFrame(f);
    }

    private void createRegisterFrame()
    {
        RegisterFrame rf = new RegisterFrame(frameNames.get(RegisterFrame.class));
        mf.addInternalFrame(rf);
    }

    private void createCodeFrame()
    {
        CodeFrame cf = new CodeFrame(frameNames.get(CodeFrame.class));
        mf.addInternalFrame(cf);
    }

    private void createStatisticsFrame()
    {
        StatisticsFrame sf = new StatisticsFrame(frameNames.get(StatisticsFrame.class));
        mf.addInternalFrame(sf);
    }

    private void createLogFrame()
    {
        LogFrame lf = new LogFrame(frameNames.get(LogFrame.class));
        mf.addInternalFrame(lf);
    }
     private void createClockCycleFrame()
    {
        ClockCycleFrame ccf = new ClockCycleFrame(frameNames.get(ClockCycleFrame.class));
        mf.addInternalFrame(ccf);
    }
     private void createCacheFrame()
     {
         CacheFrame cf = new CacheFrame(frameNames.get(CacheFrame.class));
         mf.addInternalFrame(cf);
     }
}
