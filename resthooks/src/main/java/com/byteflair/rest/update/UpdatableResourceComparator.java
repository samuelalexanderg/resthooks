package com.byteflair.rest.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by dcerecedo on 11/04/14.
 */
public class UpdatableResourceComparator {

    private static final Logger LOG=LoggerFactory.getLogger(UpdatableResourceComparator.class);

    public static ComparisonVeredict compare(final Object existing, final Object update) {
        final ComparisonVeredict result=new UpdatableResourceComparator().new ComparisonVeredict();
        final BeanWrapper existingWrapper=PropertyAccessorFactory.forBeanPropertyAccess(existing);
        final BeanWrapper updateWrapper=PropertyAccessorFactory.forBeanPropertyAccess(update);
        ReflectionUtils.doWithFields(existing.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException {
                compare(existingWrapper, updateWrapper, field, result);
            }
        }, new ReflectionUtils.FieldFilter() {
            @Override
            public boolean matches(Field field) {
                return field.getAnnotation(UpdatableField.class) == null;
            }
        });
        return result;
    }

    private static void compare(BeanWrapper existing, BeanWrapper update, Field field, ComparisonVeredict result) throws IllegalArgumentException {
        try {
            if(existing.isReadableProperty(field.getName())) {
                Object resourceValue=existing.getPropertyValue(field.getName());
                if(field.getAnnotation(JsonIgnore.class) != null) {
                    /**
                     * If the property is not being serialized it will be null when deserializing
                     * I copy the property from existing to update
                     */
                    update.setPropertyValue(field.getName(), resourceValue);
                }
                Object updateValue=update.getPropertyValue(field.getName());
                if(!areEqual(resourceValue, updateValue)) {
                    result.addField(field, resourceValue, updateValue);
                }
            }
        } catch(NotReadablePropertyException e) {
            /**
             * Never used
             */
            LOG.error("Tried to access field {}.{} but it was not readable", existing.getClass().getName(), field.getName());
        }
    }

    private static boolean areEqual(final Object one, final Object two) {
        Object first=one;
        Object second=two;
        boolean areEqual=false;
        if(one != null && two != null) {
            /**
             * In case objects are not of the same type, we
             * check if they can be converted to one another
             * in either direction. We set as first, the object
             * that is higher in the hierarchy that represents
             * the minimun commonality between the two objects
             */
            if(!one.getClass().equals(two.getClass())) {
                if(one.getClass().isAssignableFrom(two.getClass())) {
                    LOG.warn("Comparing objects of different class: {} to {}", first.getClass().getName(), second.getClass().getName());
                } else if(two.getClass().isAssignableFrom(one.getClass())) {
                    first=two;
                    second=one;
                    LOG.warn("Comparing objects of different class: {} to {}", first.getClass().getName(), second.getClass().getName());
                } else {
                    LOG.error("The objects to compare must be of the same class. Unable to compare {} with {}.", one.getClass().getName(), two.getClass().getName());
                    throw new IllegalArgumentException(String.format("The objects to compare must be of the same class. Unable to compare %s with %s.", one.getClass().getName(), two.getClass().getName()));
                }
            }
            if(first instanceof String
                  || first instanceof Boolean) {
                areEqual=first.equals(second);
            } else if(first instanceof Number) {
                areEqual=((Number) first).doubleValue() == ((Number) second).doubleValue();
            } else if(first instanceof Date) {
                areEqual=0 == ((Date) first).compareTo((Date) second);
            } else if(first instanceof Collection) {
                areEqual=UpdatableResourceComparator.getDisjointSet((Collection) first, (Collection) second).isEmpty();
            }
        } else if(one == null && two == null) {
            areEqual=true;
        }
        return areEqual;
    }

    private static Set<?> getDisjointSet(Collection<?> first, Collection<?> second) {
        Set<Object> disjoint=new HashSet<Object>();
        for(Object item : first) {
            if(!second.contains(item)) {
                disjoint.add(item);
            }
        }
        for(Object item : second) {
            if(!first.contains(item)) {
                disjoint.add(item);
            }
        }

        return disjoint;
    }

    public class ComparisonVeredict {

        private List<Field> fields=new ArrayList<>();
        private List<Object> existingValues=new ArrayList<>();
        private List<Object> updateVaues=new ArrayList<>();

        public void addField(Field field, Object existing, Object update) {
            fields.add(field);
            existingValues.add(existing);
            updateVaues.add(update);
        }

        public boolean areEqual() {
            return fields.isEmpty();
        }

        @Override
        public String toString() {
            String representation="";
            for(int i=0; i<fields.size(); i++) {
                Field field=fields.get(i);
                Object existing=existingValues.get(i);
                Object update=updateVaues.get(i);
                representation+=(i>0 ? "; " : "")+field.getName()+": "+
                      (existing != null ? existing.toString() : "null")+
                      " != "+(update != null ? update.toString() : "null");
            }
            return representation;
        }
    }
}
