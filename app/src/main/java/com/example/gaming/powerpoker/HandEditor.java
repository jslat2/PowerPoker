package com.example.gaming.powerpoker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gaming on 7/24/2015.
 */
public class HandEditor extends Fragment{
    public static ArrayList<String> selectedCards = new ArrayList<>();
    public ArrayList<String> playerCards = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.hand_editor, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (selectedCards.contains("ah") && !playerCards.contains("ah")) {
            getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("ah")) {
                getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.AH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("ah")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("ah");
                            selectedCards.add("ah");
                            getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("ah");
                        selectedCards.remove("ah");
                        getActivity().findViewById(R.id.AH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("kh") && !playerCards.contains("kh")) {
            getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("kh")) {
                getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.KH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("kh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("kh");
                            selectedCards.add("kh");
                            getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("kh");
                        selectedCards.remove("kh");
                        getActivity().findViewById(R.id.KH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("qh") && !playerCards.contains("qh")) {
            getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (playerCards.contains("qh")) {
                getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.QH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("qh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("qh");
                            selectedCards.add("qh");
                            getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("qh");
                        selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("jh") && !playerCards.contains("jh")) {
            getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("jh")) {
                getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.JH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("jh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("jh");
                            selectedCards.add("jh");
                            getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("jh");
                        selectedCards.remove("jh");
                        getActivity().findViewById(R.id.JH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("10h") && !playerCards.contains("10h")) {
            getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("10h")) {
                getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.tenH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("10h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("10h");
                            selectedCards.add("10h");
                            getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("10h");
                        selectedCards.remove("10h");
                        getActivity().findViewById(R.id.tenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("9h") && !playerCards.contains("9h")) {
            getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("9h")) {
                getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.nineH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("9h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("9h");
                            selectedCards.add("9h");
                            getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("9h");
                        selectedCards.remove("9h");
                        getActivity().findViewById(R.id.nineH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("8h") && !playerCards.contains("8h")) {
            getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("8h")) {
                getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.eightH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("8h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("8h");
                            selectedCards.add("8h");
                            getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("8h");
                        selectedCards.remove("8h");
                        getActivity().findViewById(R.id.eightH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("7h") && !playerCards.contains("7h")) {
            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("7h")) {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.sevenH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("7h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("7h");
                            selectedCards.add("7h");
                            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("7h");
                        selectedCards.remove("7h");
                        getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("6h") && !playerCards.contains("6h")) {
            getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("6h")) {
                getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.sixH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("6h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("6h");
                            selectedCards.add("6h");
                            getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("6h");
                        selectedCards.remove("6h");
                        getActivity().findViewById(R.id.sixH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("5h") && !playerCards.contains("5h")) {
            getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("5h")) {
                getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.fiveH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("5h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("5h");
                            selectedCards.add("5h");
                            getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("5h");
                        selectedCards.remove("5h");
                        getActivity().findViewById(R.id.fiveH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("4h") && !playerCards.contains("4h")) {
            getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("4h")) {
                getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.fourH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("4h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("4h");
                            selectedCards.add("4h");
                            getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("4h");
                        selectedCards.remove("4h");
                        getActivity().findViewById(R.id.fourH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("3h") && !playerCards.contains("3h")) {
            getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("3h")) {
                getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.threeH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("3h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("3h");
                            selectedCards.add("3h");
                            getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("3h");
                        selectedCards.remove("3h");
                        getActivity().findViewById(R.id.threeH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("2h") && !playerCards.contains("2h")) {
            getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("2h")) {
                getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.twoH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("2h")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("2h");
                            selectedCards.add("2h");
                            getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("2h");
                        selectedCards.remove("2h");
                        getActivity().findViewById(R.id.twoH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("as") && !playerCards.contains("as")) {
            getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("as")) {
                getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.AS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("as")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("as");
                            selectedCards.add("as");
                            getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("as");
                        selectedCards.remove("as");
                        getActivity().findViewById(R.id.AS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("ks") && !playerCards.contains("ks")) {
            getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("ks")) {
                getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.KS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("ks")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("ks");
                            selectedCards.add("ks");
                            getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("ks");
                        selectedCards.remove("ks");
                        getActivity().findViewById(R.id.KS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("qh") && !playerCards.contains("qh")) {
            getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (playerCards.contains("qh")) {
                getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.QS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("qh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("qh");
                            selectedCards.add("qh");
                            getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("qh");
                        selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("js") && !playerCards.contains("js")) {
            getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("js")) {
                getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.JS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("js")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("js");
                            selectedCards.add("js");
                            getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("js");
                        selectedCards.remove("js");
                        getActivity().findViewById(R.id.JS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("10s") && !playerCards.contains("10s")) {
            getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("10s")) {
                getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.tenS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("10s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("10s");
                            selectedCards.add("10s");
                            getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("10s");
                        selectedCards.remove("10s");
                        getActivity().findViewById(R.id.tenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("9s") && !playerCards.contains("9s")) {
            getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("9s")) {
                getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.nineS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("9s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("9s");
                            selectedCards.add("9s");
                            getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("9s");
                        selectedCards.remove("9s");
                        getActivity().findViewById(R.id.nineS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("8s") && !playerCards.contains("8s")) {
            getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("8s")) {
                getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.eightS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("8s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("8s");
                            selectedCards.add("8s");
                            getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("8s");
                        selectedCards.remove("8s");
                        getActivity().findViewById(R.id.eightS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("7s") && !playerCards.contains("7s")) {
            getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("7s")) {
                getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.sevenS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("7s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("7s");
                            selectedCards.add("7s");
                            getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("7s");
                        selectedCards.remove("7s");
                        getActivity().findViewById(R.id.sevenS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("6s") && !playerCards.contains("6s")) {
            getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("6s")) {
                getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.sixS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("6s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("6s");
                            selectedCards.add("6s");
                            getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("6s");
                        selectedCards.remove("6s");
                        getActivity().findViewById(R.id.sixS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("5s") && !playerCards.contains("5s")) {
            getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("5s")) {
                getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.fiveS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("5s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("5s");
                            selectedCards.add("5s");
                            getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("5s");
                        selectedCards.remove("5s");
                        getActivity().findViewById(R.id.fiveS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("4s") && !playerCards.contains("4s")) {
            getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("4s")) {
                getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.fourS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("4s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("4s");
                            selectedCards.add("4s");
                            getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("4s");
                        selectedCards.remove("4s");
                        getActivity().findViewById(R.id.fourS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("3s") && !playerCards.contains("3s")) {
            getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("3s")) {
                getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.threeS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("3s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("3s");
                            selectedCards.add("3s");
                            getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("3s");
                        selectedCards.remove("3s");
                        getActivity().findViewById(R.id.threeS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("2s") && !playerCards.contains("2s")) {
            getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("2s")) {
                getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
            }
            getActivity().findViewById(R.id.twoS).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("2s")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("2s");
                            selectedCards.add("2s");
                            getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("2s");
                        selectedCards.remove("2s");
                        getActivity().findViewById(R.id.twoS).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.orange_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("AD") && !playerCards.contains("AD")) {
            getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("AD")) {
                getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.AD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("AD")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("AD");
                            selectedCards.add("AD");
                            getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("AD");
                        selectedCards.remove("AD");
                        getActivity().findViewById(R.id.AD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("KD") && !playerCards.contains("KD")) {
            getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("KD")) {
                getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.KD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("KD")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("KD");
                            selectedCards.add("KD");
                            getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("KD");
                        selectedCards.remove("KD");
                        getActivity().findViewById(R.id.KD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("qh") && !playerCards.contains("qh")) {
            getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (playerCards.contains("qh")) {
                getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.QD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("qh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("qh");
                            selectedCards.add("qh");
                            getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("qh");
                        selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("JD") && !playerCards.contains("JD")) {
            getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("JD")) {
                getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.JD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("JD")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("JD");
                            selectedCards.add("JD");
                            getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("JD");
                        selectedCards.remove("JD");
                        getActivity().findViewById(R.id.JD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("10d") && !playerCards.contains("10d")) {
            getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("10d")) {
                getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.tenD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("10d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("10d");
                            selectedCards.add("10d");
                            getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("10d");
                        selectedCards.remove("10d");
                        getActivity().findViewById(R.id.tenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("9d") && !playerCards.contains("9d")) {
            getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("9d")) {
                getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.nineD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("9d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("9d");
                            selectedCards.add("9d");
                            getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("9d");
                        selectedCards.remove("9d");
                        getActivity().findViewById(R.id.nineD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("8d") && !playerCards.contains("8d")) {
            getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("8d")) {
                getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.eightD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("8d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("8d");
                            selectedCards.add("8d");
                            getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("8d");
                        selectedCards.remove("8d");
                        getActivity().findViewById(R.id.eightD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("7d") && !playerCards.contains("7d")) {
            getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("7d")) {
                getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.sevenD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("7d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("7d");
                            selectedCards.add("7d");
                            getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("7d");
                        selectedCards.remove("7d");
                        getActivity().findViewById(R.id.sevenD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("6d") && !playerCards.contains("6d")) {
            getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("6d")) {
                getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.sixD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("6d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("6d");
                            selectedCards.add("6d");
                            getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("6d");
                        selectedCards.remove("6d");
                        getActivity().findViewById(R.id.sixD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("5d") && !playerCards.contains("5d")) {
            getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("5d")) {
                getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fiveD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("5d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("5d");
                            selectedCards.add("5d");
                            getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("5d");
                        selectedCards.remove("5d");
                        getActivity().findViewById(R.id.fiveD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("4d") && !playerCards.contains("4d")) {
            getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("4d")) {
                getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fourD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("4d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("4d");
                            selectedCards.add("4d");
                            getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("4d");
                        selectedCards.remove("4d");
                        getActivity().findViewById(R.id.fourD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("3d") && !playerCards.contains("3d")) {
            getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("3d")) {
                getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.threeD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("3d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("3d");
                            selectedCards.add("3d");
                            getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("3d");
                        selectedCards.remove("3d");
                        getActivity().findViewById(R.id.threeD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("2d") && !playerCards.contains("2d")) {
            getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("2d")) {
                getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.twoD).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("2d")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("2d");
                            selectedCards.add("2d");
                            getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("2d");
                        selectedCards.remove("2d");
                        getActivity().findViewById(R.id.twoD).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("AC") && !playerCards.contains("AC")) {
            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("AC")) {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.AC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("AC")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("AC");
                            selectedCards.add("AC");
                            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("AC");
                        selectedCards.remove("AC");
                        getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("KC") && !playerCards.contains("KC")) {
            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("KC")) {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.KC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("KC")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("KC");
                            selectedCards.add("KC");
                            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("KC");
                        selectedCards.remove("KC");
                        getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("qh") && !playerCards.contains("qh")) {
            getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (playerCards.contains("qh")) {
                getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.QC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("qh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("qh");
                            selectedCards.add("qh");
                            getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("qh");
                        selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("JC") && !playerCards.contains("JC")) {
            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("JC")) {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.JC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("JC")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("JC");
                            selectedCards.add("JC");
                            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("JC");
                        selectedCards.remove("JC");
                        getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("10c") && !playerCards.contains("10c")) {
            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("10c")) {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.tenC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("10c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("10c");
                            selectedCards.add("10c");
                            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("10c");
                        selectedCards.remove("10c");
                        getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("9c") && !playerCards.contains("9c")) {
            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("9c")) {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.nineC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("9c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("9c");
                            selectedCards.add("9c");
                            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("9c");
                        selectedCards.remove("9c");
                        getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("8c") && !playerCards.contains("8c")) {
            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("8c")) {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.eightC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("8c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("8c");
                            selectedCards.add("8c");
                            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("8c");
                        selectedCards.remove("8c");
                        getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("7c") && !playerCards.contains("7c")) {
            getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("7c")) {
                getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.sevenC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("7c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("7c");
                            selectedCards.add("7c");
                            getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("7c");
                        selectedCards.remove("7c");
                        getActivity().findViewById(R.id.sevenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("6c") && !playerCards.contains("6c")) {
            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("6c")) {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.sixC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("6c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("6c");
                            selectedCards.add("6c");
                            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("6c");
                        selectedCards.remove("6c");
                        getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("5c") && !playerCards.contains("5c")) {
            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("5c")) {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fiveC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("5c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("5c");
                            selectedCards.add("5c");
                            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("5c");
                        selectedCards.remove("5c");
                        getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("4c") && !playerCards.contains("4c")) {
            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("4c")) {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.fourC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("4c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("4c");
                            selectedCards.add("4c");
                            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("4c");
                        selectedCards.remove("4c");
                        getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("3c") && !playerCards.contains("3c")) {
            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("3c")) {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.threeC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("3c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("3c");
                            selectedCards.add("3c");
                            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("3c");
                        selectedCards.remove("3c");
                        getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("2c") && !playerCards.contains("2c")) {
            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("2c")) {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
            }
            getActivity().findViewById(R.id.twoC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("2c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("2c");
                            selectedCards.add("2c");
                            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("2c");
                        selectedCards.remove("2c");
                        getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.green_button, null));
                    }
                }
            });
        }
        if (selectedCards.contains("AC") && !playerCards.contains("AC")) {
            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("AC")) {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.AC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("AC")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("AC");
                            selectedCards.add("AC");
                            getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("AC");
                        selectedCards.remove("AC");
                        getActivity().findViewById(R.id.AC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("KC") && !playerCards.contains("KC")) {
            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("KC")) {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.KC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("KC")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("KC");
                            selectedCards.add("KC");
                            getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("KC");
                        selectedCards.remove("KC");
                        getActivity().findViewById(R.id.KC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("qh") && !playerCards.contains("qh")) {
            getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        }
        else {
            if (playerCards.contains("qh")) {
                getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.QC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("qh")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("qh");
                            selectedCards.add("qh");
                            getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("qh");
                        selectedCards.remove("qh");
                        getActivity().findViewById(R.id.QC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("JC") && !playerCards.contains("JC")) {
            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("JC")) {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.JC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("JC")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("JC");
                            selectedCards.add("JC");
                            getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("JC");
                        selectedCards.remove("JC");
                        getActivity().findViewById(R.id.JC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("10c") && !playerCards.contains("10c")) {
            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("10c")) {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.tenC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("10c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("10c");
                            selectedCards.add("10c");
                            getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("10c");
                        selectedCards.remove("10c");
                        getActivity().findViewById(R.id.tenC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("9c") && !playerCards.contains("9c")) {
            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("9c")) {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.nineC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("9c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("9c");
                            selectedCards.add("9c");
                            getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("9c");
                        selectedCards.remove("9c");
                        getActivity().findViewById(R.id.nineC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("8c") && !playerCards.contains("8c")) {
            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("8c")) {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.eightC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("8c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("8c");
                            selectedCards.add("8c");
                            getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("8c");
                        selectedCards.remove("8c");
                        getActivity().findViewById(R.id.eightC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("7c") && !playerCards.contains("7c")) {
            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("7c")) {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
            }
            getActivity().findViewById(R.id.sevenH).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("7c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("7c");
                            selectedCards.add("7c");
                            getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("7c");
                        selectedCards.remove("7c");
                        getActivity().findViewById(R.id.sevenH).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.red_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("6c") && !playerCards.contains("6c")) {
            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("6c")) {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.sixC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("6c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("6c");
                            selectedCards.add("6c");
                            getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("6c");
                        selectedCards.remove("6c");
                        getActivity().findViewById(R.id.sixC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("5c") && !playerCards.contains("5c")) {
            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("5c")) {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.fiveC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("5c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("5c");
                            selectedCards.add("5c");
                            getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("5c");
                        selectedCards.remove("5c");
                        getActivity().findViewById(R.id.fiveC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("4c") && !playerCards.contains("4c")) {
            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("4c")) {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.fourC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("4c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("4c");
                            selectedCards.add("4c");
                            getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("4c");
                        selectedCards.remove("4c");
                        getActivity().findViewById(R.id.fourC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("3c") && !playerCards.contains("3c")) {
            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("3c")) {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.threeC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("3c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("3c");
                            selectedCards.add("3c");
                            getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("3c");
                        selectedCards.remove("3c");
                        getActivity().findViewById(R.id.threeC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }

        if (selectedCards.contains("2c") && !playerCards.contains("2c")) {
            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.black_button, null));
        } else {
            if (playerCards.contains("2c")) {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
            } else {
                getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
            }
            getActivity().findViewById(R.id.twoC).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!playerCards.contains("2c")) {
                        if (playerCards.size() >= 2) {
                            Toast.makeText(getActivity(), "Player can only have two cards", Toast.LENGTH_LONG).show();
                        } else {
                            playerCards.add("2c");
                            selectedCards.add("2c");
                            getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.white_button, null));
                        }
                    } else {
                        playerCards.remove("2c");
                        selectedCards.remove("2c");
                        getActivity().findViewById(R.id.twoC).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.medium_button, null));
                    }
                }
            });
        }


    }




}
