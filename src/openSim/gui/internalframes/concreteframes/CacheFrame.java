package openSim.gui.internalframes.concreteframes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.TableModel;

import openSim.gui.MainFrame;
import openSim.gui.internalframes.OpenDLXSimInternalFrame;
import openSim.gui.internalframes.concreteframes.canvas.LineCacheCanvas;
import openSim.gui.internalframes.concreteframes.canvas.TableCanvas;
import openSim.gui.internalframes.concreteframes.canvas.nWayCacheCanvas;
import openSim.gui.internalframes.factories.tableFactories.CacheTableFactory;
import openSim.gui.internalframes.util.TableSizeCalculator;
import riscvSimulator.RiscVMemory;
import riscvSimulator.caches.RiscVCache;
import riscvSimulator.caches.SimpleCache;
import riscvSimulator.caches.SingleWayCache;
import riscvSimulator.caches.lineCache.LineCache;
import riscvSimulator.caches.wayCache.nWayCache;
import riscvSimulator.values.RiscVValue32;

@SuppressWarnings("serial")
public class CacheFrame extends OpenDLXSimInternalFrame implements ActionListener{

	
	private RiscVMemory memory;
	private JTable cacheTable;
	private JRadioButtonMenuItem hexRadio;
	private JRadioButtonMenuItem binRadio;
	private JRadioButtonMenuItem decRadio;
	private JPopupMenu popup;
	private JButton button;
	private int valueFormat; // 0 = hex, 1 = bin , 2 = dec
	private TableCanvas canvas;
	
	public CacheFrame(String name) {
		super(name, false);
		valueFormat = 0;
		this.memory = MainFrame.getInstance().getSimulator().getMemory();
		initialize();
	}

	
	
	 @Override
	 protected void initialize()
	    {
		 	super.initialize();
		 
		 	JToolBar controls = new JToolBar();
		 	controls.setFloatable(false);
		 	controls.setRollover(false);
		 	controls.setFocusable(false);
		 	ButtonGroup group = new ButtonGroup();
		 	hexRadio = new JRadioButtonMenuItem("Values as Hex");
		 	binRadio = new JRadioButtonMenuItem("Values as Binary");
		 	decRadio = new JRadioButtonMenuItem("Values as Decimal");
		 	group.add(hexRadio);
		 	group.add(binRadio);
		 	group.add(decRadio);
		 	popup = new JPopupMenu();
		 	popup.add(hexRadio);
		 	popup.add(binRadio);
		 	popup.add(decRadio);
		 	button = createButton("Settings", "Show Settings", "/img/icons/tango/settings.png");
		 	button.addActionListener(this);
		 	controls.add(Box.createHorizontalGlue());
		 	controls.add(button);
		 
		 	
		 	hexRadio.addActionListener(this);
		 	binRadio.addActionListener(this);
		 	decRadio.addActionListener(this);
		 
		 	hexRadio.setSelected(true);
		 	
		 	
		 	
		 	System.out.println("Creating CacheFrame");
		 	cacheTable = new CacheTableFactory(memory.getCache()).createTable();
		 	if (memory.getCache() instanceof nWayCache)
		 		canvas = new nWayCacheCanvas(cacheTable,((nWayCache)memory.getCache()).getWayCount(),((nWayCache)memory.getCache()).getSetLength());
		 	if (memory.getCache() instanceof LineCache)
		 		canvas = new LineCacheCanvas(cacheTable,((LineCache)memory.getCache()).offsetLength,((LineCache)memory.getCache()).getSetLength());
			if (memory.getCache() instanceof SingleWayCache)
		 		canvas = new TableCanvas(cacheTable);
			if (memory.getCache() instanceof SimpleCache)
		 		canvas = new TableCanvas(cacheTable);
		 	cacheTable.setFillsViewportHeight(false);
		 	//TableSizeCalculator.setDefaultMaxTableSize(scrollpane, cacheTable,
	       //         TableSizeCalculator.SET_SIZE_BOTH);
	        //config internal frame
			add(controls,BorderLayout.NORTH);
	        add(canvas, BorderLayout.CENTER);
	    
	        pack();
	        setVisible(true);
	    }
	
	@Override
	public void update() {
		TableModel model = cacheTable.getModel();
		if (memory.getCache() instanceof SingleWayCache) {
			SingleWayCache cache = (SingleWayCache) memory.getCache();
			for (int i = 0; i < cache.getSize(); i++) {
				RiscVValue32 value = new RiscVValue32(((SingleWayCache) cache).findByte(i, 4).getUnsignedValue() << 24 | 
						(((SingleWayCache) cache).findByte(i, 3).getUnsignedValue() << 16 | 
								(((SingleWayCache) cache).findByte(i, 2).getUnsignedValue() << 8 | 
										(((SingleWayCache) cache).findByte(i, 1).getUnsignedValue()))));
				model.setValueAt(cache.findTag(i),i,SingleWayCache.tagColumn);
				model.setValueAt(value,i,SingleWayCache.valueColumn);
			}
		}
		if (memory.getCache() instanceof nWayCache) {
			nWayCache cache = (nWayCache) memory.getCache();
			nWayCacheCanvas canvas = (nWayCacheCanvas)this.canvas;
			canvas.displayEvent(cache.getLastAddress(),
					cache.getLastTag(), 
					cache.getLastSet(),
					cache.getLastWay(),
					cache.getLastSelector(),
					cache.getLastAction() == RiscVCache.READ_ACTION,
					cache.getLastHitMiss() ==  1);
			for (int i = 0 ; i < cache.getSize() ; i++) {
				for (int j = 0 ; j < cache.wayCount ; j++) {
					RiscVValue32 value = new RiscVValue32(cache.findByte(i, j, 3).getUnsignedValue() << 24 | 
							(cache.findByte(i, j, 2).getUnsignedValue() << 16 | 
									(cache.findByte(i, j, 1).getUnsignedValue() << 8 | 
											(cache.findByte(i,j, 0).getUnsignedValue()))));
					model.setValueAt("0x"+cache.findTag(i, j),i,CacheTableFactory.NWAY_WAY_OFFSET + CacheTableFactory.NWAY_TAG_OFFSET + j * CacheTableFactory.NWAY_WAY_SIZE);
					model.setValueAt(cache.getValid(i, j),i,CacheTableFactory.NWAY_WAY_OFFSET + CacheTableFactory.NWAY_VALID_OFFSET + j * CacheTableFactory.NWAY_WAY_SIZE);
					switch (valueFormat) {
						case 1 : //BIN							
							model.setValueAt("0b"+Long.toBinaryString(value.getUnsignedValue()),i,CacheTableFactory.NWAY_WAY_OFFSET + CacheTableFactory.NWAY_DATA_OFFSET + j * CacheTableFactory.NWAY_WAY_SIZE);
							break;
						case 2 :
							model.setValueAt(value.getUnsignedValue(),i,CacheTableFactory.NWAY_WAY_OFFSET + CacheTableFactory.NWAY_DATA_OFFSET + j * CacheTableFactory.NWAY_WAY_SIZE);
							break;
						default ://HEX	
							model.setValueAt("0x"+Long.toHexString(value.getUnsignedValue()),i,CacheTableFactory.NWAY_WAY_OFFSET + CacheTableFactory.NWAY_DATA_OFFSET + j * CacheTableFactory.NWAY_WAY_SIZE);
					}
					
					
				}
			}
		}
		if (memory.getCache() instanceof LineCache) {
			LineCache cache = (LineCache) memory.getCache();
			LineCacheCanvas canvas = (LineCacheCanvas)this.canvas;
			canvas.displayEvent(cache.getLastAddress(),
					cache.getLastTag(), 
					cache.getLastSet(),
					cache.getLastOffset(),
					cache.getLastSelector(),
					cache.getLastAction() == RiscVCache.READ_ACTION,
					cache.getLastHitMiss() ==  1);
		
			for (int i = 0 ; i < cache.getSize() ; i++) {
				for (int j = 0 ; j < Math.pow(2, cache.offsetLength) ; j++) {
					RiscVValue32 value = new RiscVValue32(cache.findByte(i,j, 3).getUnsignedValue() << 24 | 
							(cache.findByte(i,j, 2).getUnsignedValue() << 16 | 
									(cache.findByte(i,j, 1).getUnsignedValue() << 8 | 
											(cache.findByte(i,j, 0).getUnsignedValue()))));
					model.setValueAt("0x"+cache.findTag(i),i,CacheTableFactory.LINECACHE_TAG_COL);
					model.setValueAt(cache.getValid(i),i,CacheTableFactory.LINECACHE_VALID_COL);
					switch (valueFormat) {
					case 1 : 	//BIN
						model.setValueAt("0b"+Long.toBinaryString(value.getUnsignedValue()),i,j+CacheTableFactory.LINECACHE_DATA_OFFSET);
						break;
					case 2 : 	//DEC				
						model.setValueAt(value.getUnsignedValue(),i,j+CacheTableFactory.LINECACHE_DATA_OFFSET);
						break;
					default :	//HEX
						model.setValueAt("0x"+Long.toHexString(value.getUnsignedValue()),i,j+CacheTableFactory.LINECACHE_DATA_OFFSET);
				}
				}
			}
			
		}
		
	}

	@Override
	public void clean() {
		setVisible(false);
        dispose();
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == hexRadio) {
				valueFormat = 0;
			}
			if (e.getSource() == binRadio) {
				valueFormat = 1;
			}
			if (e.getSource() == decRadio) {
				valueFormat = 2;
			}
			if (e.getSource() == button) {
				popup.show(button,0,button.getWidth());
			}
			update();
			
	}
	
	private JButton createButton(String name, String tooltip, String icon_path) 
    {
        JButton button = new JButton();
        URL icon_url;
        if((icon_path != null) && ((icon_url = getClass().getResource(icon_path)) != null))
        {
            button.setIcon(new ImageIcon(icon_url));
        }
        else
        {
            button.setText(name);
        }
  
        button.setToolTipText(tooltip);
        button.setFocusable(false);
        return button;
    }

}
