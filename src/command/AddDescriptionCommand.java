package command;/**
 * @author mewCu
 * @description
 * @date 2019/9/26 19:47
 * @modified
 */

import middleware.DataContainer;

/**
 *@ClassName AddDescriptionCommand
 *@Description 添加描述
 *@Author mewCu
 *Date 2019/9/26 19:47
 */
public class AddDescriptionCommand extends MindCommand{

    private String model="";
    private String description="";

    @Override
    public void execute() {
        getDc().add(getModel(),getDescription());
    }

    @Override
    public void undo() {
        getDc().add(getModel(),"");
    }

    public AddDescriptionCommand(String model,String description,DataContainer dc) {
        super(dc);
        this.model=model;
        this.description=description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
