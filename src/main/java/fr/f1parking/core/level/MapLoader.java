package fr.f1parking.core.level;

import java.util.ArrayList;
import java.util.List;

import fr.f1parking.core.maps.IMap;

public class MapLoader {
	
	private static MapLoader instance;
	
	private List<IMap> maps;

	private MapLoader() {
		maps = new ArrayList<>();
	}
	
	public void addNewMap(IMap... mapList) {
		for(IMap map : mapList) if(!this.maps.contains(map)) this.maps.add(map);
	}
	
	public void removeMap(IMap map) {
		this.maps.remove(map);
	}
	
	public int getIdMapWithName(String mapName) {
		for(int i = 0; i < getMapNumber(); i++) if(this.maps.get(i).getName().equals(mapName)) return i;
		return 0;
	
	}
	
	public IMap getMap(int id) {
		if(id > getMapNumber()) id = 0;
		return this.maps.get(id);
	}
	
	public int getMapNumber() {
		return this.maps.size();
	}
	
	public static MapLoader getInstance() {
		if(instance == null) instance = new MapLoader();
		return instance;
	}
	
}
