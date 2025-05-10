package ru.irina.subscription.services.api;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.BindingResult;
import ru.irina.subscription.exceptions.ResourceNotFoundException;
import ru.irina.subscription.exceptions.ValidationException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class BaseService<T, ID> {

    protected final MessageSource messageSource;

    @Autowired
    public BaseService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    protected abstract CrudRepository<T, Long> getRepository();

    public T findById(Long id){
        return getRepository().findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(messageSource.getMessage("error.resource.id",
                        null, Locale.getDefault()), id))
        );
    }

    public List<T> findAll(){
        return (List<T>) getRepository().findAll();
    }

    public void delete(@NotNull T entity){
        getRepository().delete(entity);
    }

    public void checkValidation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                            .collect(Collectors.toList())
            );
        }
    }

    public T save(@NotNull T entity) {
        return getRepository().save(entity);
    }
}
