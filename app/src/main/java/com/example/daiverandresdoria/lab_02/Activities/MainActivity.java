package com.example.daiverandresdoria.lab_02.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daiverandresdoria.lab_02.R;
import com.example.daiverandresdoria.lab_02.adapters.FruitAdapter;
import com.example.daiverandresdoria.lab_02.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ListView listView;
    //variables para el adaptador
    private FruitAdapter listViewAdapter;
    private FruitAdapter gridViewAdapter;
    //variables para las opciones de menu
    private MenuItem itemGridView;
    private MenuItem itemListView;
    //array de objetos de Fruit
    private List<Fruit> fruits;
    //contador para nuevos elementos
    private int count = 0;
    //swicht entre listView y GridView
    private final int   SWITCH_TO_LIST_VIEW = 0;
    private final int   SWITCH_TO_GRID_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //enforcebar();
        fruits = AllFruits();
        this.listView = findViewById(R.id.listView);
        this.gridView = findViewById(R.id.gridView);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        this.listView.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);

        gridViewAdapter = new FruitAdapter(this,R.layout.grid_view_item_fruit,fruits);
        listViewAdapter = new FruitAdapter(this,R.layout.list_view_item_fruit,fruits);

        gridView.setAdapter(gridViewAdapter);
        listView.setAdapter(listViewAdapter);

        //Activa la funcion del context menu para poder borrar(registra el context para abmos)
        registerForContextMenu(this.gridView);
        registerForContextMenu(this.listView);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.ClickFruit(fruits.get(position));
    }
    public void ClickFruit(Fruit fruit){
        if (fruit.getOrigin().equals("Unknown")){
            Toast.makeText(this,"Sorry, we don't have information",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"the best fruit of "+fruit.getOrigin(),Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        this.itemListView = menu.findItem(R.id.listView);
        this.itemGridView = menu.findItem(R.id.gridView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit:
                this.addfruit(new Fruit("Added nº" + (++count),"Unknown", R.mipmap.new_icon));
                return true;
            case R.id.gridView:
                switchGridAndListView(SWITCH_TO_GRID_VIEW);
                return true;
            case R.id.listView:
                switchGridAndListView(SWITCH_TO_LIST_VIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(fruits.get(info.position).getName());
        menuInflater.inflate(R.menu.context_menu_fruits,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_fruit:
                this.deletFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void switchGridAndListView(int option){
        if (option==SWITCH_TO_LIST_VIEW){
            this.listView.setVisibility(View.VISIBLE);
            this.itemListView.setVisible(false);
            this.gridView.setVisibility(View.INVISIBLE);
            this.itemGridView.setVisible(true);
        }else if(option ==SWITCH_TO_GRID_VIEW){
            this.gridView.setVisibility(View.VISIBLE);
            this.itemGridView.setVisible(false);
            this.listView.setVisibility(View.INVISIBLE);
            this.itemListView.setVisible(true);
        }
    }
    /*public void enforcebar(){
        getSupportActionBar().setIcon(R.mipmap.fruit_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }*/
    private List<Fruit> AllFruits(){
        List<Fruit> list = new ArrayList<Fruit>(){{
          add(new Fruit("Banana","colombia",R.mipmap.banana_icon));
          add(new Fruit("cherry","Brasil",R.mipmap.cherry_icon));
          add(new Fruit("Strawberry","Venezuela",R.mipmap.strawberry_icon));
          add(new Fruit("pear","Paraguai",R.mipmap.pear_icon));
          add(new Fruit("lemon","uruguai",R.mipmap.lemon_icon));
        }};
        return list;
    }
    public void addfruit(Fruit fruit){
        this.fruits.add(fruit);
        this.listViewAdapter.notifyDataSetChanged();
        this.gridViewAdapter.notifyDataSetChanged();
    }
    public void deletFruit(int position){
        this.fruits.remove(position);
        this.listViewAdapter.notifyDataSetChanged();
        this.gridViewAdapter.notifyDataSetChanged();
    }


}
