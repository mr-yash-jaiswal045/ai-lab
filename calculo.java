package com.yash.calculo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Declare toolbar and layout variables
    MaterialToolbar toolbar;
    LinearLayout layout;

    private TextView textViewHistory;
    private TextView textViewResult;

    private Button btnAC, btnDEL, btnPercentage, btnDivide;
    private Button btn7, btn8, btn9, btnMultiply;
    private Button btn4, btn5, btn6, btnSubtract;
    private Button btn1, btn2, btn3, btnAdd;
    private Button btnBracket, btn0, btnDot, btnEqual;

    private String number=null;

    double firstNumber=0;
    double lastNumber=0;
    String status=null;
    boolean operator=false;
    DecimalFormat myFormatter=new DecimalFormat("######.######");

    String history;
    String currentResult;

    boolean dotCheck=true;
    boolean btnACControl=true;
    boolean btnEqualControl=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout = findViewById(R.id.bg);
        toolbar = findViewById(R.id.tlbr);

        // Set overflow icon for the toolbar
        toolbar.setOverflowIcon(AppCompatResources.getDrawable(this, R.drawable.baseline_more_vert_24));

        // Set a listener for the navigation icon click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message when the navigation icon is clicked
                Toast.makeText(getApplicationContext(), "Navigation icon is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.share)
                {
                    Toast.makeText(getApplicationContext(),"Share icon is clicked",Toast.LENGTH_SHORT).show(); //toast message
                }
                if(item.getItemId()==R.id.edit)
                {
                    Toast.makeText(getApplicationContext(),"Edit icon is clicked",Toast.LENGTH_SHORT).show();  //toast message
                }
                if(item.getItemId()==R.id.setting)
                {
                    Snackbar.make(layout,"Setting has been optimized",Snackbar.LENGTH_INDEFINITE).setAction("close", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
                }
                if(item.getItemId()==R.id.exit)
                {
                    showDialogueMessage(); //dialogue message
                }

                return false;
            }
        });

        // Initialize text views
        textViewHistory = findViewById(R.id.textViewHistory);
        textViewResult = findViewById(R.id.textViewResult);

        // Initialize buttons
        btnAC = findViewById(R.id.btnAC);
        btnDEL = findViewById(R.id.btnDEL);
        btnPercentage = findViewById(R.id.btnPercentage);
        btnDivide = findViewById(R.id.btnDivide);

        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnMultiply = findViewById(R.id.btnmultiply);

        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnSubtract = findViewById(R.id.btnsubtract);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnAdd = findViewById(R.id.btadd);

        btnBracket = findViewById(R.id.btnchange);
        btn0 = findViewById(R.id.btn0);
        btnDot = findViewById(R.id.btndot);
        btnEqual = findViewById(R.id.btnequal);

        //Number click listener
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });

        //Operator Click Listener methods

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                String res = history + currentResult + "+";
                textViewHistory.setText(res);
                if(operator) {
                    if (status == "multiplication") {
                        multiply();
                    } else if (status == "division") {
                        divide();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        plus();
                    }
                }
                status="sum";
                operator=false;
                number=null;
                }

        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                String res = history + currentResult + "-";
                textViewHistory.setText(res);
                if(operator) {
                    if (status == "multiplication") {
                        multiply();
                    } else if (status == "division") {
                        divide();
                    } else if (status == "sum") {
                        plus();
                    } else {
                        minus();
                    }
                }
                status="subtraction";
                operator=false;
                number=null;
            }

        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                String res = history + currentResult + "*";
                textViewHistory.setText(res);
                if(operator) {
                    if (status == "sum") {
                        plus();
                    } else if (status == "division") {
                        divide();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        multiply();
                    }
                }
                status="multiplication";
                operator=false;
                number=null;
            }

        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                String res = history + currentResult + "/";
                textViewHistory.setText(res);
                if(operator) {
                    if (status == "sum") {
                        plus();
                    } else if (status == "multiplication") {
                        multiply();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        divide();
                    }
                }
                status="division";
                operator=false;
                number=null;
            }

        });

        //other click listeners
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                String res = history + currentResult ;
                textViewHistory.setText(res);
                if(operator) {
                    if (status == "sum") {
                        plus();
                    } else if (status == "multiplication") {
                        multiply();
                    } else if (status == "subtraction") {
                        minus();
                    } else if (status == "division") {
                        divide();
                    }
                    else {
                        firstNumber=Double.parseDouble(textViewResult.getText().toString());
                    }
                }
                operator=false;
                btnEqualControl=true;
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=null;
                status=null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber=0;
                lastNumber=0;
                dotCheck=true;
                btnACControl=true;
            }
        });

        btnDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnACControl)
                {
                    textViewResult.setText("0");
                }
                else
                {
                    number=number.substring(0,number.length()-1);
                    if(number.length()==0)
                    {
                         btnDEL.setClickable(false);
                    }
                    else if(number.contains("."))
                    {
                        dotCheck=false;
                    }
                    else {
                        dotCheck=true;
                    }
                    textViewResult.setText(number);
                }


            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick (View view){
                if(dotCheck)
                {
                if (number == null) {
                    number = "0.";
                } else {
                    number = number + ".";
                }
                textViewResult.setText(number);
                dotCheck=false;
            }
            }

        });



    }

    // Method to show a dialog message when the exit icon is clicked
    private void showDialogueMessage() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Exit")
                .setMessage("Do you want to close the app?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Cancel the dialog when "No" is clicked
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close the current activity and terminate the app when "Yes" is clicked
                        finish();
                        System.exit(0);
                    }
                })
                .show();
        alertdialog.create();
    }

    //method for number click
    public void numberClick(String view)
    {
        if(number==null)
        {
           number=view;
        }
        else if(btnEqualControl)
        {
            firstNumber=0;
            lastNumber=0;
            number=view;
        }
        else {
            number=number+view;
        }
        textViewResult.setText(number);
        operator=true;
        btnACControl=false;
        btnDEL.setClickable(true);
        btnEqualControl=false;
    }


    //method for plus operator
    public void plus()
    {
        lastNumber=Double.parseDouble(textViewResult.getText().toString());
        firstNumber=firstNumber+lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));
        dotCheck=true;
    }

    //method for subtract operator
    public void minus()
    {
        if(firstNumber == 0)
        {
            firstNumber=Double.parseDouble(textViewResult.getText().toString());
        }
        else {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber-lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dotCheck=true;
    }

    //method for multiply operator
    public void multiply()
    {
        if(firstNumber == 0)
        {
            firstNumber=1;
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber*lastNumber;
        }
        else {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber*lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dotCheck=true;
    }

    //method for divide operator
    public void divide()
    {
        if(firstNumber == 0)
        {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=lastNumber/1;
        }
        else {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber/lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dotCheck=true;
    }
}
