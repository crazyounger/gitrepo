package com.learner.learncode.app.lifecycle;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liting on 2016/1/7.
 */
public class LifeCycleComponentManager implements  IComponentContainer{

    private HashMap<String,LifeCycleComponent> mComponentList ;

    public LifeCycleComponentManager(){
    }


    public static void tryAddComponentToContainer(LifeCycleComponent component,Object matrixContainer){
         tryAddComponentToContainer(component,matrixContainer,true);
    }

    private static void tryAddComponentToContainer(LifeCycleComponent component, Object matrixContainer, boolean throwException) {
        if(component == null ){
            if(throwException){
                throw  new IllegalArgumentException("component == null");
            }
        }
        if( matrixContainer == null){
            if(throwException){
                throw  new IllegalArgumentException("matrixContainer == null");
            }
        }
        if(matrixContainer instanceof  IComponentContainer){
            ((IComponentContainer)matrixContainer).addLifeCycleComponent(component);
        }else{
            if(throwException) {
                throw new IllegalArgumentException("matrixContainer should implements IComponentContainer");
            }
        }
    }

    public void addLifeCycleComponent(LifeCycleComponent component) {
        if(mComponentList == null){
            mComponentList = new HashMap<String,LifeCycleComponent>();
        }
        mComponentList.put(component.toString(),component);
    }

    public void onBecomesPartiallyInvisible() {
        if(mComponentList == null){
            return ;
        }
        Iterator<Map.Entry<String,LifeCycleComponent>> iterator = mComponentList.entrySet().iterator() ;
        while(iterator.hasNext()){
            LifeCycleComponent component = iterator.next().getValue() ;
            if(component!=null) {
                component.onBecomesPartiallyInvisible();
            }
        }
    }

    public void onBecomesVisible() {
        if(mComponentList == null){
            return ;
        }
        Iterator<Map.Entry<String,LifeCycleComponent>> iterator = mComponentList.entrySet().iterator() ;
        while(iterator.hasNext()){
            LifeCycleComponent component = iterator.next().getValue() ;
            if(component!=null) {
                component.onBecomesVisible();
            }
        }
    }

    public void onBecomesTotallyInvisible() {
        if(mComponentList == null){
            return ;
        }
        Iterator<Map.Entry<String,LifeCycleComponent>> iterator = mComponentList.entrySet().iterator() ;
        while(iterator.hasNext()){
            LifeCycleComponent component = iterator.next().getValue() ;
            if(component!=null) {
                component.onBecomesTotallyInvisible();
            }
        }
    }

    public void onBecomesVisibleFromTotallyInvisible() {
        if(mComponentList == null){
            return ;
        }
        Iterator<Map.Entry<String,LifeCycleComponent>> iterator = mComponentList.entrySet().iterator() ;
        while(iterator.hasNext()){
            LifeCycleComponent component = iterator.next().getValue() ;
            if(component!=null) {
                component.onBecomesVisibleFromTotallyInvisible();
            }
        }
    }

    public void onDestroy() {
        if(mComponentList == null){
            return ;
        }
        Iterator<Map.Entry<String,LifeCycleComponent>> iterator = mComponentList.entrySet().iterator() ;
        while(iterator.hasNext()){
            LifeCycleComponent component = iterator.next().getValue() ;
            if(component!=null) {
                component.onDestroy();
            }
        }
    }

    public void onBecomesVisibleFromPartiallyInvisible() {
        if (mComponentList == null) {
            return;
        }
        Iterator<Map.Entry<String, LifeCycleComponent>> it = mComponentList.entrySet().iterator();
        while (it.hasNext()) {
            LifeCycleComponent component = it.next().getValue();
            if (component != null) {
                component.onBecomesVisible();
            }
        }
    }
}
