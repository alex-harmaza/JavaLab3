package by.training.notebook.command;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.exception.CommandException;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public interface ICommand {
    Response execute(Request request) throws CommandException;
}
