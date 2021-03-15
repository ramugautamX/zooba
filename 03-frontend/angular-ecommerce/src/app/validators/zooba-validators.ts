import { FormControl, ValidationErrors } from "@angular/forms";

export class ZoobaValidators {
    //whitespace validation
    static notOnlyWhitespace(control: FormControl): ValidationErrors{
        
        //check to see if string only has whitespace
        if((control.value!=null)&&(control.value.trim().length===0)){
            //invalid, return error object
            return {'notOnlyWhitespace': true};
        }
        else{
            //valid, return null
            return null;
        }

    }
}
