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
package openSim.gui.internalframes.concreteframes;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import openSim.config.ArchCfg;
import openSim.gui.MainFrame;
import openSim.gui.Preference;
import openSim.gui.internalframes.OpenDLXSimInternalFrame;
import openSim.gui.internalframes.factories.tableFactories.RegisterTableFactory;
import openSim.gui.internalframes.util.TableSizeCalculator;
import riscvSimulator.RegisterFileRiscV;
import riscvSimulator.values.RiscVValue32;

@SuppressWarnings("serial")
public final class RegisterFrame extends OpenDLXSimInternalFrame
{

    private RegisterFileRiscV rs;
    private JTable registerTable;

    public RegisterFrame(String title)
    {
        super(title, false);
        this.rs = MainFrame.getInstance().getSimulator().getRegisterFile();
        initialize();
    }

    @Override
    public void update()
    {
        for (int i = 0; i < 32; ++i)
        {
            final String value;
            final RiscVValue32 register_value = rs.get(i);
            if (Preference.displayRegistersAsHex())
                value = Integer.toHexString((int) register_value.getUnsignedValue());
            else
                value = Integer.toString((int) register_value.getUnsignedValue());
            
            registerTable.getModel().setValueAt(value, i, 2);
        }
    }

    @Override
    protected void initialize()
    {
        super.initialize();
        //make the scrollpane
        registerTable = new RegisterTableFactory(rs).createTable();
        JScrollPane scrollpane = new JScrollPane(registerTable);
        scrollpane.setFocusable(false);
        registerTable.setFillsViewportHeight(true);
        TableSizeCalculator.setDefaultMaxTableSize(scrollpane, registerTable,
                TableSizeCalculator.SET_SIZE_BOTH);
        //config internal frame
        setLayout(new BorderLayout());
        add(scrollpane, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    @Override
    public void clean()
    {
        setVisible(false);
        dispose();
    }

}
