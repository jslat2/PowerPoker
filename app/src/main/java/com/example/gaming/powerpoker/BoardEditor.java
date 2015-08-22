package com.example.gaming.powerpoker;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gaming on 7/24/2015.
 */
public class BoardEditor extends Fragment{
    public ArrayList<String> boardCards = new ArrayList<>();
    HandEditor h = new HandEditor();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.hand_editor, container, false);
    }

    public void onResume() {
        super.onResume();

        Button saveButton = (Button)getActivity().findViewById(R.id.button8);
        Button homeButton = (Button)getActivity().findViewById(R.id.homeButton);
        Button bankButton = (Button)getActivity().findViewById(R.id.bankButton);
        Button sessionsButton = (Button)getActivity().findViewById(R.id.sessionsButton);
        Button calculatorButton = (Button)getActivity().findViewById(R.id.calculatorButton);
        Button notesButton = (Button)getActivity().findViewById(R.id.notesButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boardCards.size() == 1 || boardCards.size() == 2) {
                    Toast.makeText(getActivity(), "Board Must Have 0, 3, or 4 Cards", Toast.LENGTH_LONG).show();
                } else {
                    Calculator c = new Calculator();
                    FragmentManager frag = getActivity().getSupportFragmentManager();
                    frag.beginTransaction().replace(R.id.container, c, null).commit();
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardCards.size() == 1 || boardCards.size() == 2){
                    Toast.makeText(getActivity(), "Board Must Have 0, 3, or 4 Cards", Toast.LENGTH_LONG).show();
                }
                else{
                    Home h = new Home();
                    FragmentManager frag = getActivity().getSupportFragmentManager();
                    frag.beginTransaction().replace(R.id.container, h, null).commit();
                }
            }
        });

        bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardCards.size() == 1 || boardCards.size() == 2){
                    Toast.makeText(getActivity(), "Board Must Have 0, 3, or 4 Cards", Toast.LENGTH_LONG).show();
                }
                else{
                    Bankroll b = new Bankroll();
                    FragmentManager frag = getActivity().getSupportFragmentManager();
                    frag.beginTransaction().replace(R.id.container, b, null).commit();
                }
            }
        });

        sessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardCards.size() == 1 || boardCards.size() == 2){
                    Toast.makeText(getActivity(), "Board Must Have 0, 3, or 4 Cards", Toast.LENGTH_LONG).show();
                }
                else{
                    SessionLogList s = new SessionLogList();
                    FragmentManager frag = getActivity().getSupportFragmentManager();
                    frag.beginTransaction().replace(R.id.container, s, null).commit();
                }
            }
        });

        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardCards.size() == 1 || boardCards.size() == 2){
                    Toast.makeText(getActivity(), "Board Must Have 0, 3, or 4 Cards", Toast.LENGTH_LONG).show();
                }
                else{
                    Calculator c = new Calculator();
                    FragmentManager frag = getActivity().getSupportFragmentManager();
                    frag.beginTransaction().replace(R.id.container, c, null).commit();
                }
            }
        });

        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardCards.size() == 1 || boardCards.size() == 2){
                    Toast.makeText(getActivity(), "Board Must Have 0, 3, or 4 Cards", Toast.LENGTH_LONG).show();
                }
                else{
                    PlayerNotes n  = new PlayerNotes();
                    FragmentManager frag = getActivity().getSupportFragmentManager();
                    frag.beginTransaction().replace(R.id.container, n, null).commit();
                }
            }
        });


        if (MainActivity.sessionActive) {
            Chronometer timer = (Chronometer) getActivity().findViewById(R.id.timer);
            RelativeLayout timerLayout = (RelativeLayout) getActivity().findViewById(R.id.timerLayout);

            Animation myFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.flash);
            timerLayout.startAnimation(myFadeInAnimation);

            timer.setBase(SystemClock.elapsedRealtime() - MainActivity.checkpoint);
            if(!MainActivity.sessionTimerStopped) {
                timer.start();
            }
            timerLayout.setVisibility(View.VISIBLE);
        }
    }

    public void onDestroyView(){
        super.onDestroyView();
        Chronometer timer = (Chronometer)getActivity().findViewById(R.id.timer);
        MainActivity.checkpoint = MainActivity.timeToMillis(timer.getText().toString());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (MainActivity.sessionActive) {
            Chronometer timer = (Chronometer) getActivity().findViewById(R.id.timer);
            RelativeLayout timerLayout = (RelativeLayout) getActivity().findViewById(R.id.timerLayout);

            Animation myFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.flash);
            timerLayout.startAnimation(myFadeInAnimation);

            timer.setBase(SystemClock.elapsedRealtime() - MainActivity.checkpoint);
            if(!MainActivity.sessionTimerStopped) {
                timer.start();
            }
            timerLayout.setVisibility(View.VISIBLE);
        }

        if (h.selectedCards.contains("ah") && !boardCards.contains("ah")) {
            getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("ah")) {
                getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.AH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("ah")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("ah");
                            h.selectedCards.add("ah");
                            getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("ah");
                        h.selectedCards.remove("ah");
                        getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("kh") && !boardCards.contains("kh")) {
            getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("kh")) {
                getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.KH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("kh")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("kh");
                            h.selectedCards.add("kh");
                            getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("kh");
                        h.selectedCards.remove("kh");
                        getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("qh") && !boardCards.contains("qh")) {
            getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (boardCards.contains("qh")) {
                getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.QH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("qh")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("qh");
                            h.selectedCards.add("qh");
                            getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("qh");
                        h.selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("jh") && !boardCards.contains("jh")) {
            getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("jh")) {
                getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.JH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("jh")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("jh");
                            h.selectedCards.add("jh");
                            getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("jh");
                        h.selectedCards.remove("jh");
                        getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("10h") && !boardCards.contains("10h")) {
            getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("10h")) {
                getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.tenH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("10h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("10h");
                            h.selectedCards.add("10h");
                            getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("10h");
                        h.selectedCards.remove("10h");
                        getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("9h") && !boardCards.contains("9h")) {
            getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("9h")) {
                getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.nineH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("9h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("9h");
                            h.selectedCards.add("9h");
                            getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("9h");
                        h.selectedCards.remove("9h");
                        getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("8h") && !boardCards.contains("8h")) {
            getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("8h")) {
                getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.eightH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("8h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("8h");
                            h.selectedCards.add("8h");
                            getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("8h");
                        h.selectedCards.remove("8h");
                        getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("7h") && !boardCards.contains("7h")) {
            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("7h")) {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.sevenH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("7h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("7h");
                            h.selectedCards.add("7h");
                            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("7h");
                        h.selectedCards.remove("7h");
                        getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("6h") && !boardCards.contains("6h")) {
            getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("6h")) {
                getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.sixH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("6h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("6h");
                            h.selectedCards.add("6h");
                            getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("6h");
                        h.selectedCards.remove("6h");
                        getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("5h") && !boardCards.contains("5h")) {
            getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("5h")) {
                getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.fiveH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("5h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("5h");
                            h.selectedCards.add("5h");
                            getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("5h");
                        h.selectedCards.remove("5h");
                        getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("4h") && !boardCards.contains("4h")) {
            getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("4h")) {
                getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.fourH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("4h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("4h");
                            h.selectedCards.add("4h");
                            getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("4h");
                        h.selectedCards.remove("4h");
                        getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("3h") && !boardCards.contains("3h")) {
            getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("3h")) {
                getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.threeH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("3h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("3h");
                            h.selectedCards.add("3h");
                            getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("3h");
                        h.selectedCards.remove("3h");
                        getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("2h") && !boardCards.contains("2h")) {
            getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("2h")) {
                getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.twoH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("2h")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("2h");
                            h.selectedCards.add("2h");
                            getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("2h");
                        h.selectedCards.remove("2h");
                        getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("as") && !boardCards.contains("as")) {
            getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("as")) {
                getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.AS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("as")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("as");
                            h.selectedCards.add("as");
                            getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("as");
                        h.selectedCards.remove("as");
                        getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("ks") && !boardCards.contains("ks")) {
            getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("ks")) {
                getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.KS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("ks")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("ks");
                            h.selectedCards.add("ks");
                            getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("ks");
                        h.selectedCards.remove("ks");
                        getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("qs") && !boardCards.contains("qs")) {
            getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (boardCards.contains("qs")) {
                getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.QS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("qs")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("qs");
                            h.selectedCards.add("qs");
                            getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("qs");
                        h.selectedCards.remove("qs");
                        getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("js") && !boardCards.contains("js")) {
            getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("js")) {
                getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.JS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("js")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("js");
                            h.selectedCards.add("js");
                            getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("js");
                        h.selectedCards.remove("js");
                        getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("10s") && !boardCards.contains("10s")) {
            getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("10s")) {
                getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.tenS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("10s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("10s");
                            h.selectedCards.add("10s");
                            getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("10s");
                        h.selectedCards.remove("10s");
                        getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("9s") && !boardCards.contains("9s")) {
            getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("9s")) {
                getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.nineS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("9s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("9s");
                            h.selectedCards.add("9s");
                            getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("9s");
                        h.selectedCards.remove("9s");
                        getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("8s") && !boardCards.contains("8s")) {
            getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("8s")) {
                getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.eightS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("8s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("8s");
                            h.selectedCards.add("8s");
                            getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("8s");
                        h.selectedCards.remove("8s");
                        getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("7s") && !boardCards.contains("7s")) {
            getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("7s")) {
                getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.sevenS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("7s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("7s");
                            h.selectedCards.add("7s");
                            getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("7s");
                        h.selectedCards.remove("7s");
                        getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("6s") && !boardCards.contains("6s")) {
            getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("6s")) {
                getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.sixS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("6s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("6s");
                            h.selectedCards.add("6s");
                            getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("6s");
                        h.selectedCards.remove("6s");
                        getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("5s") && !boardCards.contains("5s")) {
            getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("5s")) {
                getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.fiveS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("5s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("5s");
                            h.selectedCards.add("5s");
                            getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("5s");
                        h.selectedCards.remove("5s");
                        getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("4s") && !boardCards.contains("4s")) {
            getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("4s")) {
                getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.fourS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("4s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("4s");
                            h.selectedCards.add("4s");
                            getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("4s");
                        h.selectedCards.remove("4s");
                        getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("3s") && !boardCards.contains("3s")) {
            getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("3s")) {
                getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.threeS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("3s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("3s");
                            h.selectedCards.add("3s");
                            getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("3s");
                        h.selectedCards.remove("3s");
                        getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("2s") && !boardCards.contains("2s")) {
            getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("2s")) {
                getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.twoS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("2s")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("2s");
                            h.selectedCards.add("2s");
                            getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("2s");
                        h.selectedCards.remove("2s");
                        getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("AD") && !boardCards.contains("AD")) {
            getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("AD")) {
                getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.AD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("AD")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("AD");
                            h.selectedCards.add("AD");
                            getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("AD");
                        h.selectedCards.remove("AD");
                        getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("KD") && !boardCards.contains("KD")) {
            getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("KD")) {
                getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.KD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("KD")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("KD");
                            h.selectedCards.add("KD");
                            getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("KD");
                        h.selectedCards.remove("KD");
                        getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("qd") && !boardCards.contains("qd")) {
            getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (boardCards.contains("qd")) {
                getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.QD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("qd")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("qd");
                            h.selectedCards.add("qd");
                            getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("qd");
                        h.selectedCards.remove("qd");
                        getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("JD") && !boardCards.contains("JD")) {
            getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("JD")) {
                getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.JD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("JD")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("JD");
                            h.selectedCards.add("JD");
                            getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("JD");
                        h.selectedCards.remove("JD");
                        getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("10d") && !boardCards.contains("10d")) {
            getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("10d")) {
                getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.tenD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("10d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("10d");
                            h.selectedCards.add("10d");
                            getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("10d");
                        h.selectedCards.remove("10d");
                        getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("9d") && !boardCards.contains("9d")) {
            getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("9d")) {
                getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.nineD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("9d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("9d");
                            h.selectedCards.add("9d");
                            getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("9d");
                        h.selectedCards.remove("9d");
                        getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("8d") && !boardCards.contains("8d")) {
            getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("8d")) {
                getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.eightD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("8d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("8d");
                            h.selectedCards.add("8d");
                            getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("8d");
                        h.selectedCards.remove("8d");
                        getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("7d") && !boardCards.contains("7d")) {
            getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("7d")) {
                getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.sevenD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("7d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("7d");
                            h.selectedCards.add("7d");
                            getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("7d");
                        h.selectedCards.remove("7d");
                        getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("6d") && !boardCards.contains("6d")) {
            getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("6d")) {
                getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.sixD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("6d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("6d");
                            h.selectedCards.add("6d");
                            getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("6d");
                        h.selectedCards.remove("6d");
                        getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("5d") && !boardCards.contains("5d")) {
            getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("5d")) {
                getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fiveD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("5d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("5d");
                            h.selectedCards.add("5d");
                            getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("5d");
                        h.selectedCards.remove("5d");
                        getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("4d") && !boardCards.contains("4d")) {
            getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("4d")) {
                getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fourD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("4d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("4d");
                            h.selectedCards.add("4d");
                            getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("4d");
                        h.selectedCards.remove("4d");
                        getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("3d") && !boardCards.contains("3d")) {
            getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("3d")) {
                getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.threeD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("3d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("3d");
                            h.selectedCards.add("3d");
                            getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("3d");
                        h.selectedCards.remove("3d");
                        getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("2d") && !boardCards.contains("2d")) {
            getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("2d")) {
                getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.twoD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("2d")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("2d");
                            h.selectedCards.add("2d");
                            getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("2d");
                        h.selectedCards.remove("2d");
                        getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("AC") && !boardCards.contains("AC")) {
            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("AC")) {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.AC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("AC")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("AC");
                            h.selectedCards.add("AC");
                            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("AC");
                        h.selectedCards.remove("AC");
                        getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("KC") && !boardCards.contains("KC")) {
            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("KC")) {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.KC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("KC")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("KC");
                            h.selectedCards.add("KC");
                            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("KC");
                        h.selectedCards.remove("KC");
                        getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("qc") && !boardCards.contains("qc")) {
            getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (boardCards.contains("qc")) {
                getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.QC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("qc")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("qc");
                            h.selectedCards.add("qc");
                            getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("qc");
                        h.selectedCards.remove("qc");
                        getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("JC") && !boardCards.contains("JC")) {
            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("JC")) {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.JC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("JC")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("JC");
                            h.selectedCards.add("JC");
                            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("JC");
                        h.selectedCards.remove("JC");
                        getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("10c") && !boardCards.contains("10c")) {
            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("10c")) {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.tenC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("10c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("10c");
                            h.selectedCards.add("10c");
                            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("10c");
                        h.selectedCards.remove("10c");
                        getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("9c") && !boardCards.contains("9c")) {
            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("9c")) {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.nineC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("9c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("9c");
                            h.selectedCards.add("9c");
                            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("9c");
                        h.selectedCards.remove("9c");
                        getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("8c") && !boardCards.contains("8c")) {
            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("8c")) {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.eightC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("8c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("8c");
                            h.selectedCards.add("8c");
                            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("8c");
                        h.selectedCards.remove("8c");
                        getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("7c") && !boardCards.contains("7c")) {
            getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("7c")) {
                getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.sevenC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("7c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("7c");
                            h.selectedCards.add("7c");
                            getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("7c");
                        h.selectedCards.remove("7c");
                        getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("6c") && !boardCards.contains("6c")) {
            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("6c")) {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.sixC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("6c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("6c");
                            h.selectedCards.add("6c");
                            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("6c");
                        h.selectedCards.remove("6c");
                        getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("5c") && !boardCards.contains("5c")) {
            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("5c")) {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fiveC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("5c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("5c");
                            h.selectedCards.add("5c");
                            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("5c");
                        h.selectedCards.remove("5c");
                        getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("4c") && !boardCards.contains("4c")) {
            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("4c")) {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fourC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("4c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("4c");
                            h.selectedCards.add("4c");
                            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("4c");
                        h.selectedCards.remove("4c");
                        getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("3c") && !boardCards.contains("3c")) {
            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("3c")) {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.threeC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("3c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("3c");
                            h.selectedCards.add("3c");
                            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("3c");
                        h.selectedCards.remove("3c");
                        getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("2c") && !boardCards.contains("2c")) {
            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("2c")) {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.twoC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("2c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("2c");
                            h.selectedCards.add("2c");
                            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("2c");
                        h.selectedCards.remove("2c");
                        getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }
        if (h.selectedCards.contains("AC") && !boardCards.contains("AC")) {
            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("AC")) {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.AC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("AC")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("AC");
                            h.selectedCards.add("AC");
                            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("AC");
                        h.selectedCards.remove("AC");
                        getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("KC") && !boardCards.contains("KC")) {
            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("KC")) {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.KC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("KC")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("KC");
                            h.selectedCards.add("KC");
                            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("KC");
                        h.selectedCards.remove("KC");
                        getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("qh") && !boardCards.contains("qh")) {
            getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (boardCards.contains("qh")) {
                getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.QH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("qh")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("qh");
                            h.selectedCards.add("qh");
                            getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("qh");
                        h.selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("JC") && !boardCards.contains("JC")) {
            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("JC")) {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.JC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("JC")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("JC");
                            h.selectedCards.add("JC");
                            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("JC");
                        h.selectedCards.remove("JC");
                        getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("10c") && !boardCards.contains("10c")) {
            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("10c")) {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.tenC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("10c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("10c");
                            h.selectedCards.add("10c");
                            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("10c");
                        h.selectedCards.remove("10c");
                        getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("9c") && !boardCards.contains("9c")) {
            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("9c")) {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.nineC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("9c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("9c");
                            h.selectedCards.add("9c");
                            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("9c");
                        h.selectedCards.remove("9c");
                        getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("8c") && !boardCards.contains("8c")) {
            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("8c")) {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.eightC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("8c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("8c");
                            h.selectedCards.add("8c");
                            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("8c");
                        h.selectedCards.remove("8c");
                        getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("7c") && !boardCards.contains("7c")) {
            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("7c")) {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.sevenH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("7c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("7c");
                            h.selectedCards.add("7c");
                            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("7c");
                        h.selectedCards.remove("7c");
                        getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("6c") && !boardCards.contains("6c")) {
            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("6c")) {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.sixC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("6c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("6c");
                            h.selectedCards.add("6c");
                            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("6c");
                        h.selectedCards.remove("6c");
                        getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("5c") && !boardCards.contains("5c")) {
            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("5c")) {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.fiveC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("5c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("5c");
                            h.selectedCards.add("5c");
                            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("5c");
                        h.selectedCards.remove("5c");
                        getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("4c") && !boardCards.contains("4c")) {
            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("4c")) {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.fourC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("4c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("4c");
                            h.selectedCards.add("4c");
                            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("4c");
                        h.selectedCards.remove("4c");
                        getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("3c") && !boardCards.contains("3c")) {
            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("3c")) {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.threeC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("3c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("3c");
                            h.selectedCards.add("3c");
                            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("3c");
                        h.selectedCards.remove("3c");
                        getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (h.selectedCards.contains("2c") && !boardCards.contains("2c")) {
            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (boardCards.contains("2c")) {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.twoC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!boardCards.contains("2c")) {
                        if (boardCards.size() >= 5) {
                            Toast.makeText(getActivity(), "Board cannot have more than 5 cards", Toast.LENGTH_LONG).show();
                        } else {
                            boardCards.add("2c");
                            h.selectedCards.add("2c");
                            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        boardCards.remove("2c");
                        h.selectedCards.remove("2c");
                        getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

    }


}
