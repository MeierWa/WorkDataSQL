package command;/**
 * @author mewCu
 * @description
 * @date 2019/9/26 19:49
 * @modified
 */

import middleware.DataContainer;

/**
 *@ClassName DeleteDescriptionCommand
 *@Description TODO
 *@Author mewCu
 *Date 2019/9/26 19:49
 */
public class DeleteDescriptionCommand extends BaseMindCommand {
    private String model="";
    private String description="";

    @Override
    public void execute() {
        String des=getDc().find(getModel()).getDescription();
        if(!description.isEmpty()){
            setDescription(des);
            getDc().add(getModel(),"");
        }
    }

    @Override
    public void undo() {
        getDc().add(getModel(),getDescription());
    }

    public DeleteDescriptionCommand(String model,DataContainer dc) {
        super(dc);
        this.model=model;
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
